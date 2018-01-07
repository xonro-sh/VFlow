package com.xonro.weixinpay.helper;

import com.xonro.weixinpay.enums.WechatEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * 获取微信退款通知对其加密信息进行解密
 *
 * 解密步骤如下：
 *（1）对加密串A做base64解码，得到加密串B
 *（2）对商户key做md5，得到32位小写key* ( key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置 )
 *（3）用key*对加密串B做AES-256-ECB解密（PKCS7Padding）
 * @author Alex
 * @date 2018/1/5
 */
@Component
public class AesHelper {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 解决no such provider:BC 的问题
     */
    static {
        try{
            Security.addProvider(new BouncyCastleProvider());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 加密
     * @param bytes byte型数据
     * @return 加密后字符串
     */
    private static String getBase64(byte[] bytes) {
        String s = null;
        if (bytes != null) {
            s = new BASE64Encoder().encode(bytes);
        }
        return s;
    }

    /**
     * 解密
     * @param s 需解密的字符串
     * @return 解密后的byte型数据
     */
    private byte[] getFromBase64(String s) {
        byte[] b ;
        byte[] result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = b;
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        }
        return result;
    }

    /**
     * AES加密
     *
     * @param data 数据
     * @param payKey 商家密钥
     * @return 加密后数据
     * @throws Exception 异常
     */
    public String encryptData(String data,String payKey) throws Exception {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(WechatEnum.ALGORITHM_MODE_PADDING.getValue(), "BC");
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(DigestUtils.md5Hex(payKey).toLowerCase().getBytes(), WechatEnum.ALGORITHM_AES.getValue()));
        return getBase64(cipher.doFinal(data.getBytes()));
    }

    /**
     * AES解密
     *
     * @param base64Data 数据
     * @param payKey 商家密钥
     * @return 解密后数据
     * @throws Exception 异常
     */
    public String decryptData(String base64Data,String payKey) throws Exception {
        Cipher cipher = Cipher.getInstance(WechatEnum.ALGORITHM_MODE_PADDING.getValue(), "BC");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(DigestUtils.md5Hex(payKey).toLowerCase().getBytes(), WechatEnum.ALGORITHM_AES.getValue()));
        return new String(cipher.doFinal(getFromBase64(base64Data)));
    }

}
