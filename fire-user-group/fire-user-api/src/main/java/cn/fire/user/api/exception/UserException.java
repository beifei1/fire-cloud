package cn.fire.user.api.exception;

import cn.fire.common.exception.BaseException;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 10:08
 */

public class UserException extends BaseException {

    public enum ErrorEnum implements IEnum {

        USER_NOT_EXIST(11000, "用户不存在");

        private int code;
        private String description;

        ErrorEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() { return code; }

        @Override
        public String getDescription() { return description; }

    }
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
