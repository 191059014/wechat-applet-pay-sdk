package com.hb.tools.thirdparty.wechatappletpay.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 微信统一下单接口响应数据
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayUnifiedorderResponse.java, 2020/6/23 14:25, create by huangbiao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatPayUnifiedorderResponse implements Serializable {
    // 序列化
    private static final long serialVersionUID = -6666051790512470239L;
    // 返回状态码
    private String return_code;
    // 返回信息
    private String return_msg;
    // 小程序ID
    private String appid;
    // 商户号
    private String mch_id;
    // 随机字符串
    private String nonce_str;
    // 签名
    private String sign;
    // 业务结果
    private String result_code;
    // 错误代码
    private String err_code;
    // 错误代码描述
    private String err_code_des;
    // 设备号
    private String device_info;
    // 交易类型
    private String trade_type;
    // 预支付交易会话标识
    private String prepay_id;
    // 二维码链接
    private String code_url;
}

    