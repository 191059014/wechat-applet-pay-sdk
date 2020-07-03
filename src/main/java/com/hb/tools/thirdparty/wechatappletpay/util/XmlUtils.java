package com.hb.tools.thirdparty.wechatappletpay.util;

import com.google.common.base.Charsets;
import com.hb.tools.thirdparty.wechatappletpay.model.request.WeChatPayUnifiedorderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * ========== xml工具类 ==========
 *
 * @author Mr.huang
 * @version com.gaoyang.pmarketing.pmsupbasecore.utils.XmlUtils.java, v1.0
 * @date 2019年09月17日 10时41分
 */
public class XmlUtils {

    /**
     * the constant log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

    public static void main(String[] args) throws JAXBException, IOException {
        WeChatPayUnifiedorderRequest request = WeChatPayUnifiedorderRequest.builder()
                .appid("123")
                .mch_id("456")
                .detail("detailValue")
                .build();
        String beanToXml = beanToXmlIgnoreXmlHead(request, WeChatPayUnifiedorderRequest.class);
        System.out.println("bean：" + request);
        System.out.println("bean转换xml：" + beanToXml);
        WeChatPayUnifiedorderRequest xml2Bean = xml2Bean(WeChatPayUnifiedorderRequest.class, beanToXml);
        System.out.println("bean：" + xml2Bean);
    }

    /**
     * java对象转换为xml文件
     *
     * @param obj  对象
     * @param load java对象.Class
     * @return xml文件的String
     */
    public static String beanToXml(Object obj, Class<?> load) {
        StringWriter writer = null;
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(load);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, Charsets.UTF_8.name());
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("beanToXml error，reason: {}", LogExceptionWapper.getStackTrace(e));
            }
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("beanToXml error，close writer exception: {}", LogExceptionWapper.getStackTrace(e));
                    }
                }
            }
        }
        return null;

    }

    /**
     * java对象转换为xml文件
     *
     * @param obj  对象
     * @param load java对象.Class
     * @return xml文件的String
     */
    public static String beanToXmlIgnoreXmlHead(Object obj, Class<?> load) {
        StringWriter writer = null;
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(load);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, Charsets.UTF_8.name());
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("beanToXml error，reason: {}", LogExceptionWapper.getStackTrace(e));
            }
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("beanToXml error，close writer exception: {}", LogExceptionWapper.getStackTrace(e));
                    }
                }
            }
        }
        return null;
    }


    /**
     * 将XML转为指定的POJO
     *
     * @param clazz  xml对应的javabean
     * @param xmlStr xml字符串
     * @return javabean
     */
    public static <T> T xml2Bean(Class<T> clazz, String xmlStr) {
        Object xmlObject = null;
        Reader reader = null;
        JAXBContext context = null;
        try {
            // 实例化类
            context = JAXBContext.newInstance(clazz);
            // XML 转为对象的接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // 流读取xml字符串
            reader = new StringReader(xmlStr);
            //以文件流的方式传入这个string
            xmlObject = unmarshaller.unmarshal(reader);
            return (T) xmlObject;
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("xml2Bean error，reason: {}", LogExceptionWapper.getStackTrace(e));
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("xml2Bean error，close reader exception: {}", LogExceptionWapper.getStackTrace(e));
                    }
                }
            }
        }
        return null;
    }

}
