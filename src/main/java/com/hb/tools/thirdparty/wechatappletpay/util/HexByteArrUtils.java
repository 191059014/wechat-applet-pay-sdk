package com.hb.tools.thirdparty.wechatappletpay.util;

/**
 * 十六进制与byte数组转换工具
 *
 * @author Mr.Huang
 * @version v0.1, HexByteArrUtils.java, 2020/5/28 14:38, create by huangbiao.
 */
public class HexByteArrUtils {

    /**
     * 将byte数组转换为表示16进制值的字符串
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    public static String byteArr2HexStr(byte[] bytes) throws Exception {
        int iLen = bytes.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = bytes[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串转换为二进制数组
     *
     * @param hexStr 字符串
     * @return byte数组
     */
    public static byte[] hexStr2byteArr(String hexStr) {
        if (hexStr == null || hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}

    