package com.hb.tools.thirdparty.wechatappletpay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 微信退款通知model中的reqinfo
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayUnifiedorderNotifyModel.java, 2020/6/24 14:44, create by huangbiao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatPayRefundNotifyModelReqInfo implements Serializable {
    // 序列化ID
    private static final long serialVersionUID = 1212587659585923221L;
    // 微信订单号
    private String transaction_id;
    // 商户订单号
    private String out_trade_no;
    // 微信退款单号
    private String refund_id;
    // 商户退款单号
    private String out_refund_no;
    // 订单金额
    private String total_fee;
    // 应结订单金额
    private String settlement_total_fee;
    // 申请退款金额
    private String refund_fee;
    // 退款金额
    private String settlement_refund_fee;
    // 退款状态
    private String refund_status;
    // 退款成功时间
    private String success_time;
    // 退款入账账户
    private String refund_recv_accout;
    // 退款资金来源
    private String refund_account;
    // 退款发起来源
    private String refund_request_source;
}

    