package cn.fire.gateway.util;

/**
 * @Author: wangzc
 * @Date: 2020/9/2 11:14
 */

public class Utils {

    public interface Authority {

        String ROLE_PREFIEX = "ROLE_";

        String AUTHORITY_NAME = "authorities";
    }

    public interface Security {

        String H_TIMESTAMP = "x-fire-timestamp";

        String H_NONCE = "x-fire-nonce";

        String H_SIGN = "x-fire-sign";
    }


}
