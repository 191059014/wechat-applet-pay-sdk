package com.hb.tools.thirdparty.wechatappletpay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * 微信支付通知接口
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayUnifiedorderNotifyModel.java, 2020/6/24 14:44, create by huangbiao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatPayRefundNotifyModel implements Serializable {
    // 序列化ID
    private static final long serialVersionUID = 666352630232247614L;
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
    // 加密信息
    private String req_info;
    // req_info解密后的信息
    @XmlTransient
    private WeChatPayRefundNotifyModelReqInfo reqInfo;
}

    