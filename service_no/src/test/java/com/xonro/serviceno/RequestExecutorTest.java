package com.xonro.serviceno;

import com.xonro.serviceno.bean.QrCode;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.WechatService;
import com.xonro.serviceno.web.RequestExecutor;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * @author louie
 * @date 2018-1-16
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestExecutorTest {

    @Autowired
    private UrlBuilder urlBuilder;

    @Autowired
    private WechatService wechatService;


    @Test
    public void testGetRequest() throws IOException, WechatException {
        String url = urlBuilder.buildGetTokenUrl();
        String result = new RequestExecutor(url).execute().getResponseAsObject(String.class);
        System.out.println(result);
    }

    @Test
    public void testPostRequest() throws IOException {
        QrCode qrCode = wechatService.createQrCode(new Long(1000),"abc");
        FileUtils.writeByteArrayToFile(new File("myqrcode.jpg"),qrCode.getQrCode());
    }

}
