package com.xonro.weixinpay.helper;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Alex
 * @date 2018/1/2
 */
@Component
public class VariableHelper {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public Map<String,String> removeEmptyValue(Object bean) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        try {
            Map<String,String> datas = BeanUtils.describe(bean);
            datas.remove("class");
            List<String> emptyKeys = new ArrayList<>();
            for (Map.Entry<String,String> entry:datas.entrySet()){
                String value = entry.getValue();
                if (StringUtils.isEmpty(value)){
                    emptyKeys.add(entry.getKey());
                }
            }

            for (String emptyKey : emptyKeys) {
                datas.remove(emptyKey);
            }
            return datas;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }
}
