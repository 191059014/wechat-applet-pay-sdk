package com.hb.tools.thirdparty.wechatappletpay.callback;

import com.hb.tools.thirdparty.wechatappletpay.core.service.WeChatPayService;
import com.hb.tools.thirdparty.wechatappletpay.model.WeChatPayRefundNotifyModel;
import com.hb.tools.thirdparty.wechatappletpay.model.WeChatPayRefundNotifyModelReqInfo;
import com.hb.tools.thirdparty.wechatappletpay.util.WechatCodeUtils;
import com.hb.tools.thirdparty.wechatappletpay.util.WechatPayUtils;
import com.hb.tools.thirdparty.wechatappletpay.util.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信支付退款接口异步回调
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayRefundAsynNotifyCallback.java, 2020/6/30 14:44, create by huangbiao.
 */
@RestController
@RequestMapping("/wx/notify")
public class WeChatPayRefundAsynNotifyCallback {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger("wfpserver");

    // 日志前缀
    private static final String BASELOG = "[WeChatPayRefundAsynNotifyCallback-微信退款异步回调]";

    /**
     * 微信服务
     */
    @Autowired
    private WeChatPayService weChatPayService;

    @RequestMapping("/refund")
    public String refundNotify(@RequestBody String xml) {

        LOGGER.info("{}入参={}", BASELOG, xml);

        /*
         * 解析微信通知参数
         */
        WeChatPayRefundNotifyModel notifyModel = XmlUtils.xml2Bean(WeChatPayRefundNotifyModel.class, xml);

        /*
         * 处理请求参数校验，不通过直接返回
         */
        if (notifyModel == null
                || WechatCodeUtils.ReturnCode.fail(notifyModel.getReturn_code())) {
            LOGGER.info("{}notifyModel参数校验不通过,不处理直接返回", BASELOG);
            return WechatCodeUtils.ResCodeAfterNotify.FAIL.getCode();
        }

        /*
         * 解密通知参数中的req_info
         */
        String req_info = notifyModel.getReq_info();
        String reqInfoXml = WechatPayUtils.reqInfoDecrypt(req_info, weChatPayService.getSignKey(notifyModel.getNonce_str()));
        WeChatPayRefundNotifyModelReqInfo reqInfo = XmlUtils.xml2Bean(WeChatPayRefundNotifyModelReqInfo.class, reqInfoXml);
        if (reqInfo == null) {
            LOGGER.info("{}reqInfo参数校验不通过,不处理直接返回", BASELOG);
            return WechatCodeUtils.ResCodeAfterNotify.FAIL.getCode();
        }

        // 微信支付订单号
        String transaction_id = reqInfo.getTransaction_id();

        /**
         * 分布式锁（经测试，微信异步通知存在并发）
         */
        try {
            /*
             * 幂等性校验，存在则直接返回
             */

            /*
             * 校验微信支付交易信息状态是否合法
             */

            /*
             * 校验订单信息状态是否合法
             */

            /*
             * 更新微信支付信息
             */

            /*
             * 更新订单状态
             */

            // 微信通知成功，将微信订单号放入缓存，防止重复调用，作幂等性校验

            return WechatCodeUtils.ResCodeAfterNotify.SUCCESS.getCode();
        } finally {
            LOGGER.info("{}释放分布式锁", BASELOG);
            /**
             * 释放锁
             */
        }
    }

}
