package com.hb.tools.thirdparty.wechatappletpay.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 微信退款响应信息
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayRefundResponse.java, 2020/6/23 15:06, create by huangbiao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatPayRefundResponse implements Serializable {
    // 序列化
    private static final long serialVersionUID = -3154757611042389428L;
    // 返回状态码
    @XmlElement(name = "return_code")
    private String returnCode;
    // 返回信息
    @XmlElement(name = "return_msg")
    private String returnMsg;
    // 小程序ID
    @XmlElement(name = "appid")
    private String appId;
    // 商户号
    @XmlElement(name = "mch_id")
    private String mchId;
    // 随机字符串
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    // 签名
    @XmlElement(name = "sign")
    private String sign;
    // 业务结果
    @XmlElement(name = "result_code")
    private String resultCode;
    // 错误代码
    @XmlElement(name = "err_code")
    private String errCode;
    // 错误代码描述
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    // 微信订单号
    @XmlElement(name = "transaction_id")
    private String transactionId;
    // 商户订单号
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    // 商户退款单号
    @XmlElement(name = "out_refund_no")
    private String outRefundNo;
    // 微信退款单号
    @XmlElement(name = "refund_id")
    private String refundId;
    // 退款金额
    @XmlElement(name = "refund_fee")
    private String refundFee;
    // 应结退款金额
    @XmlElement(name = "settlement_refund_fee")
    private String settlementRefundFee;
    // 标价金额
    @XmlElement(name = "total_fee")
    private String totalFee;
    // 应结订单金额
    @XmlElement(name = "settlement_total_fee")
    private String settlementTotalFee;
    // 标价币种
    @XmlElement(name = "fee_type")
    private String feeType;
    // 现金支付金额
    @XmlElement(name = "cash_fee")
    private String cashFee;
    // 现金支付币种
    @XmlElement(name = "cash_fee_type")
    private String cashFeeType;
    // 现金退款金额
    @XmlElement(name = "cash_refund_fee")
    private String cashRefundFee;
    // 代金券类型
    @XmlElement(name = "coupon_type_$n")
    private String couponTypeN;
    // 代金券退款总金额
    @XmlElement(name = "coupon_refund_fee")
    private String couponRefundFee;
    // 单个代金券退款金额
    @XmlElement(name = "coupon_refund_fee_$n")
    private String couponRefundFeeN;
    // 退款代金券使用数量
    @XmlElement(name = "coupon_refund_count")
    private String couponRefundCount;
    // 退款代金券ID
    @XmlElement(name = "coupon_refund_id_$n")
    private String couponRefundIdN;

}

    