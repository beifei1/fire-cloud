package cn.fire.oauth.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: wangzc
 * @Date: 2020/8/13 15:06
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements AuthenticatedPrincipal, Serializable {

    private Long userId;

    private String userName;

    private String mobile;

    private String avatar;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getName() {
        return mobile;
    }

}
