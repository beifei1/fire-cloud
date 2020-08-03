package cn.fire.common.web.handler;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.net.SocketTimeoutException;

/**
 * @Author: wangzc
 * @Date: 2020/7/30 16:19
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 缺少参数
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R missingParameterException(MissingServletRequestParameterException e) {
        return R.fail(e.getMessage());
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(BaseException.class)
    public R baseExceptionHandler(BaseException e) {
        return R.fail(e.getCode(),e.getMessage());
    }

    /**
     * 连接错误
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SocketTimeoutException.class)
    public R readTimeOutExceptionHandler(SocketTimeoutException e) {
        return R.fail(e.getMessage());
    }
}
