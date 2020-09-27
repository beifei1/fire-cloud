package cn.fire.message.api.exception;

import cn.fire.common.exception.BaseException;

/**
 * @Author: wangzc
 * @Date: 2020/9/22 10:36
 */

public class MessageException extends BaseException {

    public enum ErrorEnum implements IEnum {

        MESSAGE_NOT_EXIST(12000, "消息不存在");

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
    public MessageException(Integer code,String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public MessageException(Integer code,String msg,Object object) {
		super(msg);
		this.code = code;
		this.msg = msg;
		this.object = object;
	}
}
