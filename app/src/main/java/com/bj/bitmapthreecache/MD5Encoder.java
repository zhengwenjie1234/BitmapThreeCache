package com.bj.bitmapthreecache;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 郑文杰 on 2017/12/7.
 */

public class MD5Encoder {

    private static String s1;

    public static String encode(String key){
        try {
            //MD5加密
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(key.getBytes());
            //消息摘要的结果一般都是转换成16 进制字符串形式展示
            String s = new BigInteger(1, result).toString(16);
            s1 = s.toUpperCase();
            Log.e("xxx" , s1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s1;
    }
}
