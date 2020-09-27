package cn.fire.message.api.enums;

/**
 * @Author: wangzc
 * @Date: 2020/9/27 10:57
 */

public enum MessageSurviveEnum {

    YES("死亡消息"),NO("可处理消息");

	private String desc;

	private MessageSurviveEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
