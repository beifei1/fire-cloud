package cn.fire.common.exception;

import lombok.Data;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 9:31
 */

@Data
public class BaseException extends RuntimeException {

    public static final Integer INVALID_PARAMTER_ERROR = 100; //参数验证错误
    public static final Integer DATA_NOT_EXISTS = 101; //数据不存在
	public static final Integer SAVE_OBJECT_ERROR = 102; //保存对象失败
	public static final Integer UPDATE_OBJECT_ERROR = 103;//更新对象失败
	public static final Integer DATA_VERSION_ERROR = 104;//数据版本错误(乐观锁)
	public static final Integer SYSTEM_BIZ_ERROR = 105; //业务处理异常

    protected String msg;
	protected Integer code;
	protected Object object;


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

}
