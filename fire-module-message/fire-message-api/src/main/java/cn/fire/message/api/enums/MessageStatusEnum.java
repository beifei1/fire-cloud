package cn.fire.message.api.enums;

/**
 * @Author: wangzc
 * @Date: 2020/9/22 15:54
 */

public enum  MessageStatusEnum {

    WAITING_CONFIRM("等待确认"), SENDING("发送中");

    private String desc;

    MessageStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
