package com.hb.tools.thirdparty.wechatappletpay.model.request;

import com.hb.tools.thirdparty.wechatappletpay.util.CDataAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * 微信统一下单接口入参
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayUnifiedorderRequest.java, 2020/6/23 14:24, create by huangbiao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatPayUnifiedorderRequest implements Serializable {
    // 序列化
    private static final long serialVersionUID = -8704416189693728946L;
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
    // 设备号
    private String device_info;
    // 商品描述
    private String body;
    // 商品详情
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String detail;
    // 附加数据
    private String attach;
    // 商户订单号
    private String out_trade_no;
    // 标价币种
    private String fee_type;
    // 标价金额
    private String total_fee;
    // 终端IP
    private String spbill_create_ip;
    // 交易起始时间
    private String time_start;
    // 交易结束时间
    private String time_expire;
    // 订单优惠标记
    private String goods_tag;
    // 通知地址
    private String notify_url;
    // 交易类型
    private String trade_type;
    // 商品ID
    private String product_id;
    // 指定支付方式
    private String limit_pay;
    // 用户标识
    private String openid;
    // 电子发票入口开放标识
    private String receipt;
    // 场景信息
    private String scene_info;
}

    