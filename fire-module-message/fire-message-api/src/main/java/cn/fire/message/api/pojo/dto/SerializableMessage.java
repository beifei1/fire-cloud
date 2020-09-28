package cn.fire.message.api.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 传输对象
 * @Author: wangzc
 * @Date: 2020/9/28 10:03
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SerializableMessage<T extends Serializable> implements Serializable {

    /**
     * 消息Id
     */
    String messageId;

    /**
     * 数据体
     */
    T data;

}
