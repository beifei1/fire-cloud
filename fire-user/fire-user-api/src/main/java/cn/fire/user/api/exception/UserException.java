package cn.fire.user.api.exception;

import cn.fire.common.exception.BaseException;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 10:08
 */

public class UserException extends BaseException {

    public static final Integer USER_NOT_EXIST = 10001; //用户不存在

    public UserException(Integer code,String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public UserException(Integer code,String msg,Object object) {
		super(msg);
		this.code = code;
		this.msg = msg;
		this.object = object;
	}

}
