package com.xonro.vflow.web.controller;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bean.BaseResponse;
import com.xonro.vflow.bean.Menu;
import com.xonro.vflow.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex
 * @date 2018/1/23
 */
@RestController
@RequestMapping(value = "/vflow")
public class VflowController {
    @Autowired
    private MenuService menuService;

    /**
     * 增加menu
     * @param data
     * @return
     */
    @RequestMapping(value = "/addMenu",method = RequestMethod.POST)
    public BaseResponse addMenu(String data){
        return menuService.addMenu(JSON.parseObject(data, Menu.class));
    }

    /**
     * 获取menu
     * @return
     */
    @RequestMapping(value = "/getMenuByPage",method = RequestMethod.GET)
    public String getMenuByPage(Integer page, Integer limit){
        return JSON.toJSONString(menuService.getMenuByPage(page, limit));
    }

    /**
     * 获取menu
     * @return
     */
    @RequestMapping(value = "/getMenu")
    public BaseResponse getMenu(){
        return menuService.getMenu();
    }

    /**
     * 删除menu
     * @param id
     * @return
     */
    @RequestMapping(value = "/delMenu")
    public BaseResponse delMenu(String id){
        return menuService.delMenu(id);
    }

    /**
     * 根据id获取menu
     * @param id
     * @return
     */
    @RequestMapping(value = "/getMenuById")
    public BaseResponse getMenuById(String id){
        return menuService.getMenuById(id);
    }

    /**
     * 获取树形菜单结构
     * @return
     */
    @RequestMapping(value = "/getMenuByTree")
    public BaseResponse getMenuByTree(){
        return menuService.getMenuByTree();
    }

    /**
     * 根据父级代码获取其下所有此单
     * @param pNo 父级代码
     * @return
     */
    @RequestMapping(value = "/getMenuByPNo/{pNo}",method = RequestMethod.GET)
    public String getMenuByPNo(@PathVariable String pNo){
        return JSON.toJSONString(menuService.getMenuByPNo(pNo));
    }

    /**
     * 更换次啊但索引
     * @param itemNo 代码
     * @param type 类型 up or down
     * @return
     */
    @RequestMapping(value = "/changeMenuIndex")
    BaseResponse changeMenuIndex(String itemNo, String type){
        return menuService.changeMenuIndex(itemNo, type);
    }
}
