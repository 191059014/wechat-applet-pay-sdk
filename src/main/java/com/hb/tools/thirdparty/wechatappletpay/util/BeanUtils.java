package com.hb.tools.thirdparty.wechatappletpay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * bean工具
 *
 * @author Mr.Huang
 * @version v0.1, BeanUtils.java, 2020/6/24 14:39, create by huangbiao.
 */

public class BeanUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * 将对象装换为map
     *
     * @param bean bean
     * @return Map
     */
    public static <T> Map<String, String> bean2Map(T bean) {
        if (bean == null) {
            return null;
        }
        try {
            Map<String, String> map = org.apache.commons.beanutils.BeanUtils.describe(bean);
            if (map != null) {
                map.remove("class");
            }
            return map;
        } catch (Exception e) {
            LOGGER.info("beanToMap exception: {}", e);
            return null;
        }
    }
}
