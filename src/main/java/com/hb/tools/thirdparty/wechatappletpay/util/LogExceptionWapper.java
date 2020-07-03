package com.hb.tools.thirdparty.wechatappletpay.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 日志工具
 *
 * @author Mr.Huang
 * @version v0.1, LogExceptionWapper.java, 2020/6/28 9:24, create by huangbiao.
 */
public class LogExceptionWapper {

    private LogExceptionWapper() {
    }

    /**
     * 获取完整栈轨迹
     *
     * @param aThrowable 异常
     * @return 堆栈
     */
    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
}
