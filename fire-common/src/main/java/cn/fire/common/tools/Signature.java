package cn.fire.common.tools;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author: wangzc
 * @Date: 2020/9/22 11:34
 */

public class Signature {

    public static String sign(String timestamp, String nonce, String secret,String... params) {

        return DigestUtils.md5Hex("aaaaa");

    }

}
