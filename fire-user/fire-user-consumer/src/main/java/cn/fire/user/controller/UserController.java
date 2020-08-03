package cn.fire.user.controller;

import cn.fire.common.web.core.R;
import cn.fire.user.api.pojo.entity.UserDO;
import cn.fire.user.feign.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private UserServiceFeign userServiceFeignClient;



    @PostMapping("/test")
    public R<UserDO> test() {
        return R.ok(userServiceFeignClient.getById(1L));
    }
}
