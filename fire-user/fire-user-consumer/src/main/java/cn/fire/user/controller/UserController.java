package cn.fire.user.controller;

import cn.fire.common.web.core.R;
import cn.fire.user.pojo.ao.UserRegisterAO;
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


    @PostMapping("/reg")
    public R<String> register(@Valid @RequestBody UserRegisterAO param) {

        return R.ok("dfdf");
    }

}
