package cn.fire.user.api.client;

import cn.fire.user.api.pojo.entity.UserDO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 10:53
 */

@RequestMapping
public interface UserFeignClient {

    /**
     * 根据用户Id查询用户
     * @param userId
     * @return
     */
    @RequestMapping("service/user/getById")
    UserDO getById(@RequestParam(value = "userId") Long userId);


    /**
     * 根据Mobile获取对象
     * @param mobile
     * @return
     */
    @RequestMapping("service/user/getByMobile")
    UserDO getByMobile(@RequestParam(value = "mobile") String mobile);



    @RequestMapping("service/user/deleteByUserId")
    Boolean deleteByUserId(@RequestParam("userId") Long userId);

}
