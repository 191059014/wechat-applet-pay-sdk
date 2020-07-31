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
 * 微信getsignkey出参
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayGetSignKeyResponse.java, 2020/7/6 11:04, create by huangbiao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatPayGetSignKeyResponse implements Serializable {
    // serialVersionUID
    private static final long serialVersionUID = 3746200874844747948L;
    // 返回状态码
    private String return_code;
    // 返回信息
    private String return_msg;
    // 沙箱签名key
    private String sandbox_signkey;
}

    