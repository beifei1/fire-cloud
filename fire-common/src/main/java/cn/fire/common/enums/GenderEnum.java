package cn.fire.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 14:51
 */

public enum GenderEnum {


    MALE(1,"男性"),
    FEMALE(2, "女性"),
    UNKNOW(3, "未知");

    @EnumValue
    @JsonValue
    private Integer code;

    private String desc;

    GenderEnum(Integer code, String name) {
        this.code = code;
        this.desc = name;
    }

}
