package com.xonro.serviceno;

import com.alibaba.fastjson.JSON;
import com.xonro.serviceno.bean.CreateQrCode;
import com.xonro.serviceno.web.RequestExecutor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author louie
 * @date 2018-1-14
 */
public class TestCases {

    @Test
    public void testInstanceOf(){
        Object a = 1;
        System.out.println(a.getClass().equals(Integer.class));
    }

    @Test
    public void testCreateQrCode(){
        CreateQrCode createQrCode = new CreateQrCode(0L,"123456qwe");
        System.out.println(JSON.toJSONString(createQrCode));
    }

    @Test
    public void testDownloadImage() throws IOException {
        String imgUrl = "http://www.qtdebug.com/img/dog.png";
        byte[] image = new RequestExecutor(imgUrl).downloadFile();
        FileUtils.writeByteArrayToFile(new File("text.png"),image);
    }

    @Test
    public void testRandomString(){
       String randomString = RandomStringUtils.randomAlphanumeric(16);
        System.out.println(randomString);
    }

}
