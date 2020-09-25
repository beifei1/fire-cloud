package cn.fire.gateway.filter.security.enums;

import cn.fire.gateway.filter.security.AbstractProtect;
import cn.fire.gateway.filter.security.impl.*;
import cn.fire.gateway.util.SpringUtil;
import lombok.Getter;
import org.springframework.http.HttpMethod;

/**
 * 充血枚举，处理不同的验证方式
 * @Author: wangzc
 * @Date: 2020/9/25 11:23
 */

@Getter
public enum MethodEnum {

    //POST验证逻辑
    POST(HttpMethod.POST, SpringUtil.getBean(Post.class)),
    //GET验证逻辑
    GET(HttpMethod.GET,SpringUtil.getBean(Get.class)),
    //OPTIONS验证逻辑
    OPTIONS(HttpMethod.OPTIONS, SpringUtil.getBean(Options.class)),
    //DELETE验证逻辑
    DELETE(HttpMethod.DELETE, SpringUtil.getBean(Delete.class)),
    //PUT验证逻辑
    PUT(HttpMethod.PUT, SpringUtil.getBean(Put.class));

    private HttpMethod method;
    private AbstractProtect protect;

    MethodEnum(HttpMethod method, AbstractProtect protect) {
        this.method = method;
        this.protect = protect;
    }

    public static AbstractProtect getObject(HttpMethod method) {
        for (MethodEnum value : MethodEnum.values()) {
            if (value.method == method) {
                return value.getProtect();
            }
        }

        return null;
    }
}
