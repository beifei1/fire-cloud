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

@RequestMapping("/producer/user")
public interface UserFeignClient {


    @RequestMapping("/getById")
    UserDO getById(@RequestParam(value = "userId") Long userId);


    @RequestMapping("/getByMobile")
    UserDO getByMobile(@RequestParam(value = "mobile") String mobile);


    @RequestMapping("/deleteByUserId")
    Boolean deleteByUserId(@RequestParam("userId") Long userId);


    @RequestMapping("/queryByMobileAndCode")
    UserDO getByMobileAndCode(@RequestParam("mobile") String mobile,@RequestParam("smsCode") String smsCode);


    @RequestMapping("/queryByMobileAndPassword")
    UserDO getByMobileAndPassword(@RequestParam("mobile") String mobile,@RequestParam("password") String password);


    @RequestMapping("/getUserRoleByUserId")
    List<RoleDO> getRoleByUserId(@RequestParam("userId") Long userId);

}
