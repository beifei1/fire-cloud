package cn.fire.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 9:26
 */

public enum PublicEnum {

    NO("否",0),YES("是",1);

	private String desc;

	@EnumValue
	private int value;

	private PublicEnum(String desc,int value) {
		this.desc = desc;
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	public void setValue(int value) {
		this.value = value;
	}
}
