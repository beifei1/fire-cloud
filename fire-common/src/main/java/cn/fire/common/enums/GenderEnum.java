package cn.fire.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 14:51
 */

public enum GenderEnum implements IEnum {


    MALE(1,"男性"),
    FEMALE(2, "女性"),
    UNKNOW(3, "未知");

    private Integer code;

    private String desc;

    GenderEnum(Integer code, String name) {
        this.code = code;
        this.desc = name;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }
}
