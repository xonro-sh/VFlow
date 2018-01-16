package com.xonro.serviceno.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.serviceno.bean.massmessage.MassMessageResult;
import com.xonro.serviceno.bean.menu.Button;
import com.xonro.serviceno.bean.menu.MenuResult;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.MenuService;
import com.xonro.serviceno.web.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @author Alex
 * @date 2018/1/16
 */
@Service
public class MenuServiceImpl implements MenuService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UrlBuilder urlBuilder;
    /**
     * 创建菜单
     *
     * @param button 菜单按钮
     * @return 结果
     * @throws WechatException 异常
     */
    @Override
    public MenuResult menuCreate(Button button) throws WechatException {
        MenuResult menuResult;
        try {
            menuResult = new RequestExecutor(urlBuilder.buildMenuCreateUrl()).executePostRequest(JSON.toJSONString(button),MenuResult.class);
            return menuResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("001", "创建菜单失败");
        }
    }

    /**
     * 查询菜单
     *
     * @return 菜单json串
     * @throws WechatException 异常
     */
    @Override
    public String menuGet() throws WechatException {
        try {
            return new RequestExecutor(urlBuilder.buildMenuGetUrl()).executeGetRequest();
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("002", "查询菜单失败");
        }
    }

    /**
     * 删除菜单 （在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单）
     *
     * @return 删除结果
     * @throws WechatException 异常
     */
    @Override
    public MenuResult menuDel() throws WechatException {
        try {
            String result = new RequestExecutor(urlBuilder.buildMenuDelUrl()).executeGetRequest();
            System.err.println("result=="+result);
            return JSON.parseObject(result, MenuResult.class);
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("003", "删除菜单失败");
        }
    }

    /**
     * 创建个性化菜单
     *
     * @param button 菜单按钮
     * @return 创建结果 若成功返回menuid
     * @throws WechatException 异常
     */
    @Override
    public MenuResult menuAddConditional(Button button) throws WechatException {
        MenuResult menuResult;
        try {
            menuResult = new RequestExecutor(urlBuilder.buildMenuConditionalUrl()).executePostRequest(JSON.toJSONString(button),MenuResult.class);
            return menuResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("004", "创建个性化菜单失败");
        }
    }

    /**
     * 删除个性化菜单
     *
     * @param menuId 菜单id
     * @return 删除结果
     * @throws WechatException 异常
     */
    @Override
    public MenuResult menuDelConditional(String menuId) throws WechatException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("menuid", menuId);
        MenuResult menuResult;
        try {
            menuResult = new RequestExecutor(urlBuilder.buildMenuConditionalDelUrl()).executePostRequest(JSON.toJSONString(dataParams),MenuResult.class);
            return menuResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("005", "删除个性化菜单失败");
        }
    }

    /**
     * 测试个性化菜单匹配结果
     *
     * @param userId 粉丝的openID或者其微信号
     * @return 菜单json串
     * @throws WechatException 异常
     */
    @Override
    public String menuTryMatch(String userId) throws WechatException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("user_id", userId);
        try {
            return new RequestExecutor(urlBuilder.buildMenuTryMatch()).executePostRequest(JSON.toJSONString(dataParams));
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("006", "个性化菜单匹配结果失败");
        }
    }

    /**
     * 获取自定义菜单配置
     *
     * @return 菜单json串
     * @throws WechatException 异常
     */
    @Override
    public String getCurrentSelfMenu() throws WechatException {
        try {
            return new RequestExecutor(urlBuilder.buildGetCurrentSelfMenuUrl()).executeGetRequest();
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("007", "查询自定义菜单配置失败");
        }
    }


}
