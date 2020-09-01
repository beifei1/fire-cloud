package cn.fire.oauth.service.impl;

import cn.fire.oauth.feign.UserFeign;
import cn.fire.oauth.pojo.dto.UserDTO;
import cn.fire.oauth.service.IUserService;
import cn.fire.user.api.exception.UserException;
import cn.fire.user.api.pojo.entity.RoleDO;
import cn.fire.user.api.pojo.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wangzc
 * @Date: 2020/8/7 10:50
 */

@Slf4j
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private UserFeign userFeign;


    @Override
    public UserDTO getByMobileAndCode(String mobile, String smsCode) throws UserException {
        UserDO userDO = userFeign.getByMobileAndCode(mobile, smsCode);
        if (Objects.isNull(userDO)) {
            return null;
        }

        UserDTO user = UserDTO.builder().build();
        BeanUtils.copyProperties(userDO, user);

        List<String> roleNames = userFeign.getRoleByUserId(user.getId()).stream().map(RoleDO::getRoleName).map(n -> "ROLE_" + n).collect(Collectors.toList());
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(roleNames, ",")));
        return user;
    }


    @Override
    public UserDTO loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserDO dbUser = userFeign.getByMobile(userName);
        if (Objects.isNull(dbUser)) {
            throw new UsernameNotFoundException("mobile not found");
        }
        UserDTO user = UserDTO.builder().build();
        BeanUtils.copyProperties(dbUser, user);


        List<String> roleNames = userFeign.getRoleByUserId(user.getId()).stream().map(RoleDO::getRoleName).map(n -> "ROLE_" + n).collect(Collectors.toList());

        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(roleNames, ",")));
        return user;
    }
}
