package cn.fire.common.web.handler;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.core.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wangzc
 * @Date: 2020/7/30 16:19
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R missingParameterException(MissingServletRequestParameterException e) {
        return R.fail(e.getMessage());
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(BaseException.class)
    public R baseExceptionHandler(BaseException e) {
        return R.fail(e.getCode(),e.getMessage());
    }
}
