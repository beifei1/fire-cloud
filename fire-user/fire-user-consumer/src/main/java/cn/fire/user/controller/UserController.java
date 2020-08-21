package cn.fire.user.controller;

import cn.fire.common.web.core.R;
import cn.fire.common.web.core.Request;
import cn.fire.user.api.pojo.entity.UserDO;
import cn.fire.user.feign.UserServiceFeign;
import cn.fire.user.pojo.ao.UserLoginAO;
import cn.fire.user.pojo.ao.UserRegisterAO;
import cn.fire.user.pojo.vo.UserDetailVO;
import cn.fire.user.pojo.vo.UserLoginVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @Author: wangzc
 * @Date: 2020/7/29 16:35
 */

@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器")
@ApiSupport(author = "beifei")
public class UserController {


    @Autowired
    private UserServiceFeign userServiceFeign;


    @PutMapping("/register")
    @ApiOperation("用户注册")
    public R register(@Valid @RequestBody Request<UserRegisterAO> param) {
        return R.ok();
    }


    @GetMapping("/{userId}")
    @ApiOperation("用户详情")
    public R<UserDetailVO> detail(@PathVariable("userId") Long userId) {
        UserDO user = userServiceFeign.getById(userId);

        UserDetailVO userDetail = UserDetailVO.builder().build();
        BeanUtils.copyProperties(user,userDetail);

        return R.ok(userDetail);
    }


    @DeleteMapping("/{userId}")
    @ApiOperation("删除用户")
    public R<Boolean> delete(@PathVariable("userId") Long userId) {
        Boolean b = userServiceFeign.deleteByUserId(userId);
        System.out.println(b);
        return R.ok(userServiceFeign.deleteByUserId(userId));
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public R<UserLoginVO> login(@Valid @RequestBody Request<UserLoginAO> param) {

        return R.ok();
    }
}
