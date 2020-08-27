package cn.fire.common.web.core.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: wangzc
 * @Date: 2020/8/27 11:18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Base请求对象")
public class ID {

    @Valid
    @ApiModelProperty("Id")
    @NotEmpty(message = "主键Id不能为空")
    private Long objectId;

}
