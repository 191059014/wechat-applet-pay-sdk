package com.hb.tools.thirdparty.wechatappletpay.callback;

import com.hb.tools.thirdparty.wechatappletpay.model.WeChatPayUnifiedorderNotifyModel;
import com.hb.tools.thirdparty.wechatappletpay.util.WechatCodeUtils;
import com.hb.tools.thirdparty.wechatappletpay.util.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信支付统一下单接口异步回调
 *
 * @author Mr.Huang
 * @version v0.1, WeChatPayUnifiedorderAsynNotifyCallback.java, 2020/6/30 14:44, create by huangbiao.
 */
@RestController
@RequestMapping("/wx/notify")
public class WeChatPayUnifiedorderAsynNotifyCallback {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger("wfpserver");

    /**
     * 基础日志
     */
    private static final String BASELOG = "[WeChatPayUnifiedorderAsynNotifyCallback-微信支付统一下单异步回调]";

    @RequestMapping("/unifiedorder")
    public String unifiedorderNotify(@RequestBody String xml) {

        LOGGER.info("{}入参={}", BASELOG, xml);

        // 解析微信通知参数
        WeChatPayUnifiedorderNotifyModel weChatPayUnifiedorderNotifyModel = XmlUtils.xml2Bean(WeChatPayUnifiedorderNotifyModel.class, xml);

        // 处理请求参数校验，不通过直接返回
        if (weChatPayUnifiedorderNotifyModel == null) {
            LOGGER.info("{}参数校验不通过,不处理直接返回", BASELOG);
            return WechatCodeUtils.ResCodeAfterNotify.FAIL.getCode();
        }

        // 微信支付订单号
        String transaction_id = weChatPayUnifiedorderNotifyModel.getTransaction_id();

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
