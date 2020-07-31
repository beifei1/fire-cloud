package cn.fire.common.web.handler;

import cn.fire.common.web.core.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: wangzc
 * @Date: 2020/7/30 16:19
 */

@ResponseBody
@ControllerAdvice
public class CommonExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R missingParameterException(MissingServletRequestParameterException e) {
        return R.fail(e.getMessage());
    }


}
