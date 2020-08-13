package cn.fire.common.exception;

import lombok.Data;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 9:31
 */

@Data
public class BaseException extends RuntimeException {

    public enum BaseErrorEnum implements IEnum {

        INVALID_PARAMTER_ERROR(10000, "参数验证错误"),
        RECORD_NOT_EXISTS(10001,"数据不存在"),
        SAVE_OBJECT_ERROR(10002,"保存数据失败"),
        UPDATE_OBJECT_ERROR(10003,"更新数据失败"),
        DATA_VERSION_ERROR(10004,"数据版本错误"),
        SYSTEM_BIZ_ERROR(10005,"业务处理异常"),
        INVALID_TOKEN(10006,"token无效"),
        TOKEN_UNAUTHORIZAD(10007,"token权限不足"),
        MESSAGE_NOT_ALLOWED(10008,"不允许的请求方法");

        private int code;
        private String description;

        BaseErrorEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() { return code; }

        @Override
        public String getDescription() { return description; }
    }

    protected String msg;
	protected Integer code;
	protected Object object;

	public static BaseException instance(Enum<? extends IEnum> em) {
	    IEnum e = (IEnum)em;
	    return new BaseException(e.getCode(),e.getDescription());
    }

	public static BaseException instance(Integer code,String msg) {
	    return new BaseException(code,msg);
    }

    public static BaseException instance(String msg) {
	    return new BaseException(msg);
    }


    public BaseException(Integer code, String msg, Object... args) {
        super(String.format(msg, args));
        this.code = code;
        this.msg = String.format(msg, args);
    }

	public BaseException(Integer code,String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public BaseException(Integer code,String msg,Object object) {
		super(msg);
		this.code = code;
		this.msg = msg;
		this.object = object;
	}

	public BaseException(){
		super();
	}

	public BaseException(Throwable throwable){
		super(throwable);
	}

	public BaseException(String message){
		this.msg = message;
	}

	protected interface IEnum {

	    int getCode();

	    String getDescription();

    }

}
