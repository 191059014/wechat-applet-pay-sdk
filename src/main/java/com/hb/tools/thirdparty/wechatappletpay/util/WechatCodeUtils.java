package com.hb.tools.thirdparty.wechatappletpay.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信响应码接口
 *
 * @author Mr.Huang
 * @version v0.1, WechatCodeUtils.java, 2020/7/2 11:18, create by huangbiao.
 */
public class WechatCodeUtils {

    /**
     * 微信接口响应报文中的return_code字段
     */
    @AllArgsConstructor
    public enum ReturnCode {

        SUCCESS("SUCCESS", "成功"),
        FAIL("FAIL", "失败");

        // 响应码
        private String code;
        // 响应信息
        private String message;

        public static boolean fail(String code) {
            return !SUCCESS.code.equals(code);
        }

    }

    /**
     * 微信接口响应报文中的result_code字段
     */
    @AllArgsConstructor
    public enum ResultCode {

        SUCCESS("SUCCESS", "成功"),
        FAIL("FAIL", "失败");

        // 响应码
        private String code;
        // 响应信息
        private String message;

        public static boolean fail(String code) {
            return !SUCCESS.code.equals(code);
        }

    }

    /**
     * 查询订单接口响应报文中的trade_state字段
     */
    @AllArgsConstructor
    public enum OrderQueryTradeState {

        SUCCESS("SUCCESS", "支付成功"),
        REFUND("REFUND", "转入退款"),
        NOTPAY("NOTPAY", "未支付"),
        CLOSED("CLOSED", "已关闭"),
        REVOKED("REVOKED", "已撤销（刷卡支付）"),
        USERPAYING("USERPAYING", "用户支付中"),
        PAYERROR("PAYERROR", "支付失败(其他原因，如银行返回失败)");

        // 响应码
        private String code;
        // 响应信息
        private String message;

        public static boolean success(String code) {
            return SUCCESS.code.equals(code);
        }

        public static boolean fail(String code) {
            return !success(code);
        }

    }

    /**
     * 查询订单接口响应报文中的trade_state字段
     */
    @AllArgsConstructor
    public enum RefundRefundStatus {

        SUCCESS("SUCCESS", "退款成功"),
        CHANGE("CHANGE", "退款异常"),
        REFUNDCLOSE("REFUNDCLOSE", "退款关闭");

        // 响应码
        private String code;
        // 响应信息
        private String message;

        public static boolean fail(String code) {
            return !SUCCESS.code.equals(code);
        }

    }

    /**
     * 接收到微信通知后，需返回给微信的响应码
     */
    @AllArgsConstructor
    @Getter
    public enum ResCodeAfterNotify {

        SUCCESS("SUCCESS", "成功"),
        FAIL("FAIL", "失败");

        // 响应码
        private String code;
        // 响应信息
        private String message;

    }

}
