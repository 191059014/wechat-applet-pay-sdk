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
 * 微信申请退款入参
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayRefundRequest.java, 2020/6/23 14:51, create by huangbiao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatPayRefundRequest implements Serializable {
    // 序列化
    private static final long serialVersionUID = -6731737888354557741L;
    // 小程序ID
    private String appid;
    // 商户号
    private String mch_id;
    // 随机字符串
    private String nonce_str;
    // 签名
    private String sign;
    // 签名类型
    private String sign_type;
    // 微信订单号
    private String transaction_id;
    // 商户订单号
    private String out_trade_no;
    // 商户退款单号
    private String out_refund_no;
    // 订单金额
    private String total_fee;
    // 退款金额
    private String refund_fee;
    // 货币种类
    private String refund_fee_type;
    // 退款原因
    private String refund_desc;
    // 退款资金来源
    private String refund_account;
    // 退款结果通知url
    private String notify_url;
}

    