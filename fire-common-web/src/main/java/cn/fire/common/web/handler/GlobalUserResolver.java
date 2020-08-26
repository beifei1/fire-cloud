package cn.fire.common.web.handler;

import cn.fire.common.web.core.request.JUser;
import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
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

        if (param.getParameterType().isAssignableFrom(JUser.class) && param.hasParameterAnnotation(cn.fire.common.web.anno.UserProfile.class)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer container,
                                  NativeWebRequest request,
                                  WebDataBinderFactory binderFactory) throws Exception {

        String authorization = StringUtils.isBlank(request.getHeader("Authorization")) ? null : request.getHeader("Authorization").replace("Bearer", "").trim();

        if (StringUtils.isNotBlank(authorization)) {
            Jwt jwt = JwtHelper.decode(authorization);
            if (jwt != null) {
                JSONObject jsonClaims = JSONObject.parseObject(jwt.getClaims());
                JUser profile = new JUser();
                profile.setGender(jsonClaims.getInteger("gender"));
                profile.setMobile(jsonClaims.getString("mobile"));
                profile.setUserId(jsonClaims.getLong("user_id"));
                profile.setUserName(jsonClaims.getString("user_name"));

                return profile;
            }
        }
        return null;
    }
}
