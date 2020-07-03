package com.hb.tools.thirdparty.wechatappletpay.core.service.impl;

import com.hb.tools.thirdparty.wechatappletpay.core.integration.WeChatPayIntegration;
import com.hb.tools.thirdparty.wechatappletpay.core.service.WeChatPayService;
import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayOrderQueryRequest;
import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayRefundRequest;
import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayUnifiedorderRequest;
import com.hb.tools.thirdparty.wechatappletpay.model.response.WeChatPayOrderQueryResponse;
import com.hb.tools.thirdparty.wechatappletpay.model.response.WeChatPayRefundResponse;
import com.hb.tools.thirdparty.wechatappletpay.model.response.WeChatPayUnifiedorderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信小程序支付service实现类
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayServiceImpl.java, 2020/6/28 9:24, create by huangbiao.
 */
@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    @Autowired
    private WeChatPayIntegration weChatPayIntegration;

    @Override
    public WeChatPayUnifiedorderResponse unifiedorder(WeChatPayUnifiedorderRequest request) {
        return weChatPayIntegration.unifiedorder(request);
    }

    @Override
    public WeChatPayOrderQueryResponse orderQuery(WeChatPayOrderQueryRequest request) {
        return weChatPayIntegration.orderQuery(request);
    }

    @Override
    public WeChatPayRefundResponse refund(WeChatPayRefundRequest request) {
        return weChatPayIntegration.refund(request);
    }

}

    