package com.xonro.weixinpay.helper;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Alex
 * @date 2018/1/2
 */
@Component
public class VariableHelper {
    public Map<String,String> removeEmptyValue(Map<String,String> datas){
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
    }
}
