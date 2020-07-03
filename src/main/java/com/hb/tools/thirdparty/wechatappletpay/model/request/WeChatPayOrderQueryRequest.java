package com.hb.tools.thirdparty.wechatappletpay.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 微信订单查询接口入参
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayOrderQueryRequest.java, 2020/6/23 14:24, create by huangbiao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatPayOrderQueryRequest implements Serializable {
    // 序列化
    private static final long serialVersionUID = 8198496710400130067L;
    // 小程序ID
    private String appid;
    // 商户号
    private String mch_id;
    // 微信订单号
    private String transaction_id;
    // 商户订单号
    private String out_trade_no;
    // 随机字符串
    private String nonce_str;
    // 签名
    private String sign;
    // 签名类型
    private String sign_type;
}

    