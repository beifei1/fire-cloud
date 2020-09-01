package cn.fire.oauth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;


/**
 * @Author: wangzc
 * @Date: 2020/8/7 10:57
 */

@Api(tags = "认证授权控制器")
@RestController
@RequestMapping("/oauth")
public class AuthorizationController {


    @Autowired
    private KeyPair keyPair;

    /**
     * 对外暴漏公匙
     * @return
     */
    @RequestMapping("/rsa/public")
    public Map<String,Object> getKey() {
        return new JWKSet(new RSAKey.Builder((RSAPublicKey)keyPair.getPublic()).build()).toJSONObject();
    }

}
