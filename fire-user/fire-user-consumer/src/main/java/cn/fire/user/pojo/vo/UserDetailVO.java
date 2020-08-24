package cn.fire.user.pojo.vo;

import cn.fire.common.enums.GenderEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: wangzc
 * @Date: 2020/8/5 16:15
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("用户详情响应体")
public class UserDetailVO {

    private Long id;

    private String userName;

    private String mobile;

    private GenderEnum gender;

    private LocalDateTime createTime;

}
