package cn.fire.user.controller;

import cn.fire.common.web.anno.UserProfile;
import cn.fire.common.web.core.request.JUser;
import cn.fire.common.web.core.response.R;
import cn.fire.common.web.core.request.Request;
import cn.fire.user.api.pojo.entity.UserDO;
import cn.fire.user.feign.UserServiceFeign;
import cn.fire.user.pojo.ao.UserLoginAO;
import cn.fire.user.pojo.ao.UserRegisterAO;
import cn.fire.user.pojo.vo.UserDetailVO;
import cn.fire.user.pojo.vo.UserLoginVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


/**
 * @Author: wangzc
 * @Date: 2020/7/29 16:35
 */

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器")
public class UserController {

    @Autowired
    private UserServiceFeign userServiceFeign;

    /**
     * 注册
     * @param param
     * @return
     */
    @PostMapping("/reg")
    @ApiOperation("用户注册")
    @ApiOperationSupport(author = "beifei")
    public R register(@Valid @RequestBody UserRegisterAO param) {
        return R.ok();
    }

    /**
     * 用户详情
     * @param jUser
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation("用户资料")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperationSupport(author = "beifei")
    public R<UserDetailVO> detail(@ApiIgnore @UserProfile JUser jUser) {

        log.info("=======================jwt user: {}",jUser.toString());

        UserDO user = userServiceFeign.getById(jUser.getUserId());
        UserDetailVO userDetail = UserDetailVO.builder().build();
        BeanUtils.copyProperties(user,userDetail);

        return R.ok(userDetail);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    @ApiOperation("删除用户")
    @PreAuthorize("hasAuthority('super')")
    @ApiOperationSupport(author = "beifei")
    public R<Boolean> delete(@PathVariable("userId") Long userId) {
        Boolean bool = userServiceFeign.deleteByUserId(userId);
        System.out.println(bool);
        return R.ok(bool);
    }

    /**
     * 用户登录
     * @param param
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ApiOperationSupport(author = "beifei")
    public R<UserLoginVO> login(@Valid @RequestBody UserLoginAO param) {

        return R.ok();
    }


    /**
     * 更新用户资料
     * @param userId
     * @param param
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(("更新用户资料"))
    @ApiOperationSupport(author = "beifei")
    public R<Boolean> update(@Valid @RequestBody UserLoginAO param) {
        return R.ok();
    }
}
