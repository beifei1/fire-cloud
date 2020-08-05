package cn.fire.user.controller;

import cn.fire.common.web.core.R;
import cn.fire.common.web.core.Request;
import cn.fire.user.feign.UserServiceFeign;
import cn.fire.user.pojo.ao.UserLoginAO;
import cn.fire.user.pojo.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @Author: wangzc
 * @Date: 2020/7/29 16:35
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserServiceFeign userServiceFeign;


    @PostMapping("/test")
    public R<String> test() {
        return R.ok(userServiceFeign.getById(3L).toString());
    }


    @PostMapping("/login")
    public R<UserLoginVO> login(@Valid @RequestBody Request<UserLoginAO> param) {

        return R.ok();
    }
}
