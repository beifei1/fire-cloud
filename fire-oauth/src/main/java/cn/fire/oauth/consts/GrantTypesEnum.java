package cn.fire.oauth.consts;

/**
 * @Author: wangzc
 * @Date: 2020/8/14 16:01
 */

public enum GrantTypesEnum {

    PASSWORD("password"), SMS("sms"),REFRESH_TOKEN("refresh_token");

    private String name;

    GrantTypesEnum(String name) {
        this.name = name;
    }
}
