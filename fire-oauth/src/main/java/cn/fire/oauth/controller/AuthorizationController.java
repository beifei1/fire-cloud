package cn.fire.oauth.controller;

import cn.fire.common.web.core.R;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: wangzc
 * @Date: 2020/8/7 10:57
 */

@Api(tags = "授权控制器")
@RestController
@RequestMapping("/auth")
public class AuthorizationController {


    @GetMapping("/login")
    @ApiOperationSupport(author = "beifei")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type",value = "授权类型(password)",required = true),
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true),
            @ApiImplicitParam(name = "userType",value = "用户类型",required = true)
    })
    public R<String> login(
            @Valid @RequestParam("grant_type") String grantType,
            @Valid @RequestParam("username") String userName,
            @Valid @RequestParam("password") String password,
            @Valid @RequestParam("user_type") Integer userType) {

        return R.ok("sfdsdfsdf");
    }

}
