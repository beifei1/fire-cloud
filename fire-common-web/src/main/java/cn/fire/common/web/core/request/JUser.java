package cn.fire.common.web.core.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: wangzc
 * @Date: 2020/8/26 9:44
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JUser {

    private Long userId;

    private String userName;

    private String mobile;

    private Integer gender;

}
