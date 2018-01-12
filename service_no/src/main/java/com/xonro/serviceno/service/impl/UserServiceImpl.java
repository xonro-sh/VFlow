package com.xonro.serviceno.service.impl;

import com.xonro.serviceno.bean.user.UserInfo;
import com.xonro.serviceno.dao.UserRepository;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.UserService;
import com.xonro.serviceno.web.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author louie
 * @date 2018-1-12
 * 用户管理
 */
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UrlBuilder urlBuilder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo getUserInfo(String openId) {
        String url = urlBuilder.buildUserInfoUrl(openId);
        try {
            return new RequestExecutor(url).executeGetRequest(UserInfo.class);
        } catch (WechatException e) {
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveUser(UserInfo userInfo) {
        userRepository.save(userInfo);
    }

    @Override
    public void deleteUser(UserInfo userInfo) {
        userRepository.delete(userInfo);
    }

}
