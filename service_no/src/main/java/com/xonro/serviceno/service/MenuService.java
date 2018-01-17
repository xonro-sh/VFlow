package com.xonro.serviceno.service;

import com.xonro.serviceno.bean.menu.Button;
import com.xonro.serviceno.bean.menu.MenuResult;
import com.xonro.serviceno.exception.WechatException;

/**
 * 菜单管理相关接口
 * @author Alex
 * @date 2018/1/16
 */
public interface MenuService {
    /**
     * 创建菜单
     * @param button 菜单按钮
     * @return 结果
     * @throws WechatException 异常
     */
    MenuResult menuCreate(Button button) throws WechatException;

    /**
     * 查询菜单
     * @return 菜单json串
     * @throws WechatException 异常
     */
    String menuGet() throws WechatException;

    /**
     * 删除菜单 （在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单）
     * @return 删除结果
     * @throws WechatException 异常
     */
    MenuResult menuDel() throws WechatException;

    /**
     * 创建个性化菜单
     * @param button 菜单按钮
     * @return 创建结果 若成功返回menuid
     * @throws WechatException 异常
     */
    MenuResult menuAddConditional(Button button) throws WechatException;

    /**
     * 删除个性化菜单
     * @param menuId 菜单id
     * @return 删除结果
     * @throws WechatException 异常
     */
    MenuResult menuDelConditional(String menuId) throws WechatException;

    /**
     * 测试个性化菜单匹配结果
     * @param userId 粉丝的openID或者其微信号
     * @return 菜单json串
     * @throws WechatException 异常
     */
    String menuTryMatch(String userId) throws WechatException;

    /**
     * 获取自定义菜单配置
     * @return 菜单json串
     * @throws WechatException 异常
     */
    String getCurrentSelfMenu() throws WechatException;
}
