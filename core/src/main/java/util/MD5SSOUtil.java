package util;

import java.security.MessageDigest;

/**
 * @Description: MD5加密
 * @author: ljy
 * @date: 2021年06月02日 10:44
 * @email 15810874514@163.com
 */

public class MD5SSOUtil {


    /*
     *@Description: MD5SSOUtil.MD5  MD5
     *@Param: s
     *@return:
     *@Author: ljy
     *@Date: 2021/6/2 10:45
     *@email: 15810874514@163.com
     *
     **/


    public final static String MD5(String s) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

    