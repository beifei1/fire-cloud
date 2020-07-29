package cn.fire.user.controller;

import cn.fire.common.web.core.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangzc
 * @Date: 2020/7/29 16:35
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("/reg")
    public R<String> register() {

        return R.ok("dfdf");
    }

}
