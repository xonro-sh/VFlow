package com.xonro.vflow.helper;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author Alex
 * @date 2018/1/23
 */
@Component
public class WebHelper {
    /**
     * 请求数据to bean （layui专用）
     * @param request 请求数据
     * @param bean bean
     * @param <T> 泛型
     * @return bean
     */
    public <T> T requstToBean(HttpServletRequest request, Class<T> bean) {
        T t = null;
        try {
            t = bean.newInstance();
            Enumeration parameterNames = request.getParameterNames();
            //写一个日期转换器
            DateConverter convert = new DateConverter();
            //限定日期的格式字符串数组
            String[] patterns = { "yyyyMMdd", "yyyy-MM-dd" };
            convert.setPatterns(patterns);
            ConvertUtils.register(convert, Date.class);
            while (parameterNames.hasMoreElements()) {
                String name = (String) parameterNames.nextElement();
                String value = request.getParameter(name);
                //使用BeanUtils来设置对象属性的值
                BeanUtils.setProperty(t, name, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }
}
