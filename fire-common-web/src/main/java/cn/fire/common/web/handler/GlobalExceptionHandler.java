package cn.fire.common.web.handler;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.response.R;
import com.google.common.collect.Lists;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.net.SocketTimeoutException;

/**
 * @Author: wangzc
 * @Date: 2020/7/30 16:19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 400
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingServletRequestParameterException.class, HttpMessageNotReadableException.class})
    public R missingParameterException(MissingServletRequestParameterException e) {
        return R.fail(e.getMessage());
    }

    /**
     * 400
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidException(MethodArgumentNotValidException e) {
        java.util.List<String> msgs = Lists.newArrayList();
        e.getBindingResult().getFieldErrors().stream().forEach(field -> {
            msgs.add(field.getDefaultMessage());
        });
        return R.fail(BaseException.BaseErrorEnum.INVALID_PARAMTER_ERROR.getCode(),StringUtils.join(msgs,','));
    }

    /**
     * 200
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BaseException.class)
    public R baseExceptionHandler(BaseException e) {
        return R.fail(e.getCode(),e.getMessage());
    }

    /**
     * 500
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SocketTimeoutException.class)
    public R readTimeOutExceptionHandler(SocketTimeoutException e) {
        return R.fail(e.getMessage());
    }

    /**
     * 405
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R methodNotAllowedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return R.fail(BaseException.BaseErrorEnum.MESSAGE_NOT_ALLOWED.getCode(), e.getMessage());
    }

}
