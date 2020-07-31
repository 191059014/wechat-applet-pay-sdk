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
 * 微信getsignkey入参
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayGetSignKeyRequest.java, 2020/7/6 11:04, create by huangbiao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatPayGetSignKeyRequest implements Serializable {
    // serialVersionUID
    private static final long serialVersionUID = 574374968685086267L;
    // 商户号
    private String mch_id;
    // 随机字符串
    private String nonce_str;
    // 签名
    private String sign;
}

    