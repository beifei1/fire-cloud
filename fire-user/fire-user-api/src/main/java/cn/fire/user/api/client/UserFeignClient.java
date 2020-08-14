package cn.fire.user.api.client;

import cn.fire.user.api.pojo.entity.RoleDO;
import cn.fire.user.api.pojo.entity.UserDO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 10:53
 */

@RequestMapping
public interface UserFeignClient {


    @RequestMapping("service/user/getById")
    UserDO getById(@RequestParam(value = "userId") Long userId);


    @RequestMapping("service/user/getByMobile")
    UserDO getByMobile(@RequestParam(value = "mobile") String mobile);


    @RequestMapping("service/user/deleteByUserId")
    Boolean deleteByUserId(@RequestParam("userId") Long userId);


    @RequestMapping("service/user/queryByMobileAndCode")
    UserDO getByMobileAndCode(@RequestParam("mobile") String mobile,@RequestParam("smsCode") String smsCode);


    @RequestMapping("service/user/queryByMobileAndPassword")
    UserDO getByMobileAndPassword(@RequestParam("mobile") String mobile,@RequestParam("password") String password);


    @RequestMapping("service/user/getUserRoleByUserId")
    List<RoleDO> getRoleByUserId(@RequestParam("userId") Long userId);

}
