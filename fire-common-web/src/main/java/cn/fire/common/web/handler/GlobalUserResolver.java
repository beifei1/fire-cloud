package cn.fire.common.web.handler;

import cn.fire.common.web.core.request.JUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Author: wangzc
 * @Date: 2020/8/26 9:43
 */
public class GlobalUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter param) {

        if (param.getParameterType().isAssignableFrom(JUser.class) &&
                param.hasParameterAnnotation(cn.fire.common.web.anno.UserProfile.class)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer container,
                                  NativeWebRequest request,
                                  WebDataBinderFactory binderFactory) throws Exception {
        JUser profile = new JUser();
        profile.setGender(1);
        profile.setMobile("18631431162");
        profile.setUserId(1L);
        profile.setUserName("王志超");

        return profile;
    }
}
