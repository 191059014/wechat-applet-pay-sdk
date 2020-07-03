package com.hb.tools.thirdparty.wechatappletpay.util;

import com.google.common.base.Stopwatch;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 微信支付工具类
 *
 * @author Mr.Huang
 * @version v0.1, WechatPayUtils.java, 2020/6/24 15:32, create by huangbiao.
 */
public class WechatPayUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatPayUtils.class);

    /**
     * 数字或字母
     */
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 随机数工具类
     */
    private static final Random RANDOM = new SecureRandom();

    /**
     * 微信签名：通过签名算法计算得出的签名值
     */
    private static final String FIELD_SIGN = "sign";

    /**
     * 微信签名类型：MD5
     */
    public static final String SIGN_TYPE_MD5 = "MD5";

    /**
     * 微信签名类型：HMACSHA256
     */
    public static final String SIGN_TYPE_HMACSHA256 = "HMACSHA256";

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";

    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data     待签名数据
     * @param key      API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(Map<String, String> data, String key, String signType) {
        String baseLog = "[WechatPayUtils-generateSignature-获取微信支付签名]";
        LOGGER.info("{}入参：{}={}={}", baseLog, data, key, signType);
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(FIELD_SIGN)) {
                continue;
            }
            String v = data.get(k);
            if (v != null && v.trim().length() > 0) { // 参数值为空，则不参与签名
                sb.append(k).append("=").append(v.trim()).append("&");
            }
        }
        sb.append("key=").append(key);
        String signBefore = sb.toString();
        LOGGER.info("{}签名前参数列表：{}", baseLog, signBefore);
        String sign = null;
        if (SIGN_TYPE_MD5.equals(signType)) {
            String md5 = md5ToString(signBefore);
            sign = md5 != null ? md5.toUpperCase() : null;
        } else if (SIGN_TYPE_HMACSHA256.equals(signType)) {
            sign = HMACSHA256(signBefore, key);
        }
        LOGGER.info("{}签名结果：{}", baseLog, sign);
        return sign;
    }

    /**
     * 进行http请求
     *
     * @param url      请求地址
     * @param data     参数信息
     * @param useCert  是否使用证书
     * @param mchId    商户ID，当useCert为false的时候可不传
     * @param certPath 证书路径，当useCert为false的时候可不传
     * @return 返回结果
     */
    public static String request(String url, String data, boolean useCert, String mchId, String certPath) {
        LOGGER.info("http请求开始，入参：{}={}={}={}={}", url, data, useCert, mchId, certPath);
        Stopwatch sw = Stopwatch.createStarted();
        String result = "";
        try {
            BasicHttpClientConnectionManager connectionManager = buildBasicHttpClientConnectionManager(useCert, mchId, certPath);
            if (connectionManager == null) {
                throw new Exception("构建的BasicHttpClientConnectionManager为空");
            }
            HttpClient httpClient = HttpClientBuilder.create()
                    .setConnectionManager(connectionManager)
                    .build();
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10 * 1000).setConnectTimeout(10 * 1000).build();
            httpPost.setConfig(requestConfig);
            StringEntity postEntity = new StringEntity(data, Charsets.UTF_8.name());
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(postEntity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, Charsets.UTF_8.name());
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("请求失败，原因：{}", LogExceptionWapper.getStackTrace(e));
            }
        }
        LOGGER.info("http请求结束，耗时：{}ms，结果：{}", sw.elapsed(TimeUnit.MILLISECONDS), result);
        return result;
    }

    /**
     * 解密req_info
     *
     * @param source 待解密的字符串
     * @param key    加密或解密的key
     * @return 解密后的字符串
     */
    public static String reqInfoDecrypt(String source, String key) {
        String result = null;
        try {
            byte[] keyBytes = md5(key);
            String base64Decode = new String(Base64Utils.decodeFromString(source), Charsets.ISO_8859_1.toString());
            byte[] bytes = aes(base64Decode.getBytes(Charsets.ISO_8859_1.toString()), keyBytes, Cipher.DECRYPT_MODE);
            result = new String(bytes, Charsets.UTF_8.toString());
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("解密req_info失败，原因：{}", LogExceptionWapper.getStackTrace(e));
            }
        }
        LOGGER.info("req_info解密结果：{}", result);
        return result;
    }

    /**
     * 加密req_info
     *
     * @param source 待加密的字符串
     * @param key    加密或解密的key
     * @return 加密后的字符串
     */
    public static String reqInfoEncrypt(String source, String key) {
        String result = null;
        try {
            byte[] keyBytes = md5(key);
            byte[] bytes = aes(source.getBytes(), keyBytes, Cipher.ENCRYPT_MODE);
            String aes = new String(bytes, Charsets.ISO_8859_1.toString());
            result = Base64Utils.encodeToString(aes.getBytes(Charsets.ISO_8859_1.toString()));
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("加密req_info失败，原因：{}", LogExceptionWapper.getStackTrace(e));
            }
        }
        LOGGER.info("req_info加密结果：{}", result);
        return result;
    }

    /**
     * 加密或者解密
     *
     * @param message  待加密或解密的字节数组
     * @param keyBytes 加密或解密的key字节数组
     * @param mode     加密或者解密模式
     * @return 字节数组
     */
    private static byte[] aes(byte[] message, byte[] keyBytes, int mode) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        // 创建密码器
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
        // 初始化
        cipher.init(mode, keySpec);
        return cipher.doFinal(message);
    }

    /**
     * 获取证书流信息
     *
     * @param certPath 证书路径
     * @return 证书流信息
     */
    private static InputStream getCertStream(String certPath) {
        InputStream is = null;
        try {
            File file = new File(certPath);
            is = new FileInputStream(file);
            byte[] certDataBytes = new byte[(int) file.length()];
            is.read(certDataBytes);
            return new ByteArrayInputStream(certDataBytes);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("获取证书流信息失败，原因：{}", LogExceptionWapper.getStackTrace(e));
            }
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("关闭流失败，原因：{}", LogExceptionWapper.getStackTrace(e));
                    }
                }
            }
        }

    }

    /**
     * 构建BasicHttpClientConnectionManager对象
     *
     * @param useCert  是否使用证书
     * @param mchId    商户ID
     * @param certPath
     * @return BasicHttpClientConnectionManager对象
     */
    private static BasicHttpClientConnectionManager buildBasicHttpClientConnectionManager(boolean useCert, String mchId, String certPath) {
        BasicHttpClientConnectionManager connManager;
        if (useCert) {
            try {
                InputStream certStream = getCertStream(certPath);
                // 证书
                char[] password = mchId.toCharArray();
                KeyStore ks = KeyStore.getInstance("PKCS12");
                ks.load(certStream, password);

                // 实例化密钥库 & 初始化密钥工厂
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(ks, password);

                // 创建 SSLContext
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                        sslContext,
                        new String[]{"TLSv1"},
                        null,
                        new DefaultHostnameVerifier());

                return new BasicHttpClientConnectionManager(
                        RegistryBuilder.<ConnectionSocketFactory>create()
                                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                                .register("https", sslConnectionSocketFactory)
                                .build(),
                        null,
                        null,
                        null
                );
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("构建BasicHttpClientConnectionManager对象失败，原因：{}", LogExceptionWapper.getStackTrace(e));
                }
                return null;
            }

        }
        return new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );
    }

    /**
     * 生成 MD5 字符串
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    private static String md5ToString(String data) {
        try {
            return HexByteArrUtils.byteArr2HexStr(md5(data));
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("生成MD5签名失败，原因：{}", LogExceptionWapper.getStackTrace(e));
            }
            return null;
        }
    }

    /**
     * 生成 MD5 字节数组
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    private static byte[] md5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(data.getBytes("UTF-8"));
    }

    /**
     * 生成 HMACSHA256
     *
     * @param data 待处理数据
     * @param key  密钥
     * @return 加密结果
     */
    private static String HMACSHA256(String data, String key) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("生成HMACSHA256签名失败，原因：{}", LogExceptionWapper.getStackTrace(e));
            }
            return null;
        }
    }

}

    