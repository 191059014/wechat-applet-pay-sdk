package com.hb.tools.thirdparty.wechatappletpay.core.integration.impl;

import com.hb.tools.thirdparty.wechatappletpay.core.integration.WeChatPayIntegration;
import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayGetSignKeyRequest;
import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayOrderQueryRequest;
import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayRefundRequest;
import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayUnifiedorderRequest;
import com.hb.tools.thirdparty.wechatappletpay.model.response.WeChatPayGetSignKeyResponse;
import com.hb.tools.thirdparty.wechatappletpay.model.response.WeChatPayOrderQueryResponse;
import com.hb.tools.thirdparty.wechatappletpay.model.response.WeChatPayRefundResponse;
import com.hb.tools.thirdparty.wechatappletpay.model.response.WeChatPayUnifiedorderResponse;
import com.hb.tools.thirdparty.wechatappletpay.util.BeanUtils;
import com.hb.tools.thirdparty.wechatappletpay.util.WechatPayUtils;
import com.hb.tools.thirdparty.wechatappletpay.util.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 微信小程序支付相关功能实现类
 *
 * @author Mr.Huang
 * @version v0.1, WxPayIntegrationImpl.java, 2020/6/24 14:39, create by huangbiao.
 */
@Component
public class WeChatPayIntegrationImpl implements WeChatPayIntegration {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatPayIntegrationImpl.class);

    /**
     * 小程序的appId
     */
    @Value("${wx.appId}")
    private String appId;

    /**
     * 小程序支付商户ID
     */
    @Value("${wx.mchId}")
    private String mchId;

    /**
     * 小程序商户API密钥
     */
    @Value("${wx.mch.apiKey}")
    private String apiKey;

    /**
     * 服务器ip
     */
    @Value("${wx.caller.serverIp}")
    private String callerServerIp;

    /**
     * 下单请求地址
     */
    @Value("${wx.unifiedorder.url}")
    private String unifiedorder_url;

    /**
     * 下单回调地址
     */
    @Value("${wx.unifiedorder.notify.url}")
    private String unifiedorder_notify_url;

    /**
     * 查询订单请求地址
     */
    @Value("${wx.orderquery.url}")
    private String orderquery_url;

    /**
     * 申请退款请求地址
     */
    @Value("${wx.refund.url}")
    private String refund_url;

    /**
     * 申请退款回调地址
     */
    @Value("${wx.refund.notify.url}")
    private String refund_notify_url;

    /**
     * 证书地址
     */
    @Value("${wx.cert.base64}")
    private String certBase64;

    /**
     * getsignkey接口的请求地址
     */
    @Value("${wx.getsignkey.url}")
    private String getsignkey_url;

    /**
     * 请求地址前缀
     */
    @Value("${wx.pay.url.host}")
    private String host;

    /**
     * 是否是沙箱环境
     */
    @Value("${wx.pay.env.sandbox}")
    private boolean isSandBox;

    @Override
    public WeChatPayUnifiedorderResponse unifiedorder(WeChatPayUnifiedorderRequest request) {
        String baseLog = "[WxPayIntegrationImpl-unifiedorder-统一下单]";
        LOGGER.info("{}入参：{}", baseLog, request);
        /*
         * 1. 组装请求参数
         */
        request.setAppid(appId);// 小程序id
        request.setMch_id(mchId);// 商户ID
        request.setSign_type(null);// 签名类型，非必填，默认为MD5
        request.setSpbill_create_ip(callerServerIp);// 终端IP
        request.setNotify_url(unifiedorder_notify_url);// 通知地址
        request.setTrade_type("JSAPI");// 交易类型，小程序使用"JSAPI"
        if (isSandBox) {
            request.setTotal_fee("101");// 沙箱环境要求填写固定金额
        }
        request.setSign(WechatPayUtils.generateSignature(BeanUtils.bean2Map(request), getSignKey(request.getNonce_str()), WechatPayUtils.SIGN_TYPE_MD5));// 签名，放在最后，所有字段参与签名
        /*
         * 2. 将参数信息转换为xml
         */
        String beanToXml = XmlUtils.beanToXml(request, WeChatPayUnifiedorderRequest.class);
        /*
         * 3. 请求微信统一下单接口
         */
        String body = WechatPayUtils.request(WechatPayUtils.getFullRequestUrl(host, unifiedorder_url, isSandBox), beanToXml, false, null, null);
        /*
         * 4. 将返回结果转换为javabean
         */
        WeChatPayUnifiedorderResponse response = null;
        if (!StringUtils.isEmpty(body)) {
            response = XmlUtils.xml2Bean(WeChatPayUnifiedorderResponse.class, body);
        }
        LOGGER.info("{}出参：{}", baseLog, response);
        return response;
    }

    @Override
    public WeChatPayOrderQueryResponse orderQuery(WeChatPayOrderQueryRequest request) {
        String baseLog = "[WxPayIntegrationImpl-orderQuery-查询订单]";
        LOGGER.info("{}入参：{}", baseLog, request);
        /*
         * 1. 组装请求参数
         */
        request.setAppid(appId);// 小程序id
        request.setMch_id(mchId);// 商户ID
        request.setSign_type(null);// 签名类型，非必填，默认为MD5
        request.setSign(WechatPayUtils.generateSignature(BeanUtils.bean2Map(request), getSignKey(request.getNonce_str()), WechatPayUtils.SIGN_TYPE_MD5));// 签名，放在最后，所有字段参与签名
        /*
         * 2. 将参数信息转换为xml
         */
        String beanToXml = XmlUtils.beanToXml(request, WeChatPayOrderQueryRequest.class);
        /*
         * 3. 请求微信统一下单接口
         */
        String body = WechatPayUtils.request(WechatPayUtils.getFullRequestUrl(host, orderquery_url, isSandBox), beanToXml, false, null, null);
        /*
         * 4. 将返回结果转换为javabean
         */
        WeChatPayOrderQueryResponse response = null;
        if (!StringUtils.isEmpty(body)) {
            response = XmlUtils.xml2Bean(WeChatPayOrderQueryResponse.class, body);
        }
        LOGGER.info("{}出参：{}", baseLog, response);
        return response;
    }

    @Override
    public WeChatPayRefundResponse refund(WeChatPayRefundRequest request) {
        String baseLog = "[WxPayIntegrationImpl-refund-申请退款]";
        LOGGER.info("{}入参：{}", baseLog, request);
        /*
         * 1. 组装请求参数
         */
        request.setAppid(appId);// 小程序id
        request.setMch_id(mchId);// 商户ID
        request.setSign_type(null);// 签名类型，非必填，默认为MD5
        request.setNotify_url(refund_notify_url);// 通知地址
        if (isSandBox) {
            request.setTotal_fee("101");// 沙箱环境要求填写固定金额
        }
        request.setSign(WechatPayUtils.generateSignature(BeanUtils.bean2Map(request), getSignKey(request.getNonce_str()), WechatPayUtils.SIGN_TYPE_MD5));// 签名，放在最后，所有字段参与签名
        /*
         * 2. 将参数信息转换为xml
         */
        String beanToXml = XmlUtils.beanToXml(request, WeChatPayRefundRequest.class);
        /*
         * 3. 请求微信统一下单接口
         */
        String body = WechatPayUtils.request(WechatPayUtils.getFullRequestUrl(host, refund_url, isSandBox), beanToXml, true, mchId, certBase64);
        /*
         * 4. 将返回结果转换为javabean
         */
        WeChatPayRefundResponse response = null;
        if (!StringUtils.isEmpty(body)) {
            response = XmlUtils.xml2Bean(WeChatPayRefundResponse.class, body);
        }
        LOGGER.info("{}出参：{}", baseLog, response);
        return response;
    }

    /**
     * 获取沙箱签名key
     *
     * @param nonceStr 随机字符串
     * @return 沙箱签名key
     */
    @Override
    public String getSignKey(String nonceStr) {
        return isSandBox ? getSignKey(WeChatPayGetSignKeyRequest.builder().nonce_str(nonceStr).mch_id(mchId).build()) : apiKey;
    }

    /**
     * 获取沙箱签名key
     *
     * @return 沙箱签名key
     */
    private String getSignKey(WeChatPayGetSignKeyRequest request) {
        String baseLog = "[WxPayIntegrationImpl-getSignKey-获取沙箱签名key]";
        LOGGER.info("{}入参：{}", baseLog, request);
        /*
         * 1. 组装请求参数
         */
        request.setSign(WechatPayUtils.generateSignature(BeanUtils.bean2Map(request), apiKey, WechatPayUtils.SIGN_TYPE_MD5));// 签名，放在最后，所有字段参与签名
        /*
         * 2. 将参数信息转换为xml
         */
        String beanToXml = XmlUtils.beanToXml(request, WeChatPayGetSignKeyRequest.class);
        /*
         * 3. 请求微信统一下单接口
         */
        String body = WechatPayUtils.request(WechatPayUtils.getFullRequestUrl(host, getsignkey_url, true), beanToXml, false, null, null);
        /*
         * 4. 将返回结果转换为javabean
         */
        WeChatPayGetSignKeyResponse response = XmlUtils.xml2Bean(WeChatPayGetSignKeyResponse.class, body);
        /*
         * 5. 返回沙箱签名key
         */
        String signKey = response == null ? null : response.getSandbox_signkey();
        LOGGER.info("{}结果：{}", baseLog, signKey);
        return signKey;
    }

}

    