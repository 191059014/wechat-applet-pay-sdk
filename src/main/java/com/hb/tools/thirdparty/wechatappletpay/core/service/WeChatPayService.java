package com.hb.tools.thirdparty.wechatappletpay.core.service;

import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayOrderQueryRequest;
import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayRefundRequest;
import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayUnifiedorderRequest;
import com.hb.tools.thirdparty.wechatappletpay.model.response.WeChatPayOrderQueryResponse;
import com.hb.tools.thirdparty.wechatappletpay.model.response.WeChatPayRefundResponse;
import com.hb.tools.thirdparty.wechatappletpay.model.response.WeChatPayUnifiedorderResponse;

/**
 * 微信小程序支付service接口
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayService.java, 2020/6/28 9:23, create by huangbiao.
 */
public interface WeChatPayService {

    /**
     * 统一下单接口
     *
     * @param request 请求参数
     * @return 响应参数
     */
    WeChatPayUnifiedorderResponse unifiedorder(WeChatPayUnifiedorderRequest request);

    /**
     * 微信支付订单查询
     *
     * @param request 请求参数
     * @return 响应参数
     */
    WeChatPayOrderQueryResponse orderQuery(WeChatPayOrderQueryRequest request);

    /**
     * 申请退款接口
     *
     * @param request 请求参数
     * @return 响应参数
     */
    WeChatPayRefundResponse refund(WeChatPayRefundRequest request);

    /**
     * 获取沙箱签名key
     *
     * @param nonceStr 随机字符串
     * @return 沙箱签名key
     */
    String getSignKey(String nonceStr);

}
