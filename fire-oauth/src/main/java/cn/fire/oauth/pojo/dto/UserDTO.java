package cn.fire.oauth.pojo.dto;

import cn.fire.common.enums.GenderEnum;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: wangzc
 * @Date: 2020/8/13 15:06
 */

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements UserDetails, Serializable {

    private Long id;

    private String userName;

    private String password;

    private String mobile;

    private Integer gender;

    private String avatar;

    private Integer locked;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mobile;
    }

    @Override
    public boolean isAccountNonExpired() {
        return locked == 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return locked == 0;
    }

    @Override
    public boolean isEnabled() {
        return locked == 0;
    }
}
