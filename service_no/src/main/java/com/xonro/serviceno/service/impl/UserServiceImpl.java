package com.xonro.serviceno.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.serviceno.bean.TableResponse;
import com.xonro.serviceno.bean.user.UserInfo;
import com.xonro.serviceno.bean.user.UserListResult;
import com.xonro.serviceno.dao.UserRepository;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.UserService;
import com.xonro.serviceno.web.RequestExecutor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author louie
 * @date 2018-1-12
 * 用户管理
 */
@Service
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
            return new RequestExecutor(url).execute().getResponseAsObject(UserInfo.class);
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

    /**
     * 批量获取用户基本信息
     *
     * @param openId 普通用户的标识，对当前公众号唯一
     * @param page 第几页
     * @param rows 每页多少条数据
     * @return
     */
    @Override
    public TableResponse getUserInfoList(String openId, Integer page, Integer rows){
        Sort sort = new Sort(Sort.Direction.DESC,"subscribeTime");
        Pageable pages = new PageRequest(page-1, rows, sort);
        Page<UserInfo> userInfos = userRepository.findAll(pages);

        if (userInfos.getTotalElements() == 0 ){
            List<UserInfo> userInfosAll;
            userInfosAll = getUserInfoFromWechat(openId);
            userRepository.save(userInfosAll);
            //再执行一遍查询
            userInfos = userRepository.findAll(pages);
        }
        return new TableResponse(0, "", userInfos.getTotalElements(),userInfos.getContent());

    }

    /**
     * 从微信获取用户信息（批量分批获取）
     * @return
     * @throws WechatException
     * @param openId
     */
    private List<UserInfo> getUserInfoFromWechat(String openId){
        UserListResult userListResults;
        String userInfos;
        List<UserInfo> userInfosAll = new ArrayList<>();
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        try {
            userListResults = new RequestExecutor(urlBuilder.buildGetUserListUrl(openId)).execute().getResponseAsObject(UserListResult.class);
            List<Map<String, String>> openList = new ArrayList<>();
            if (userListResults.getData().getOpenId().size()!=0){
                for (String openid: userListResults.getData().getOpenId()){
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("openid", openid);
                    openList.add(map);
                    if (openList.size()==100){
                        dataParams.put("user_list", openList);
                        //每次只能获取100条
                        userInfos = new RequestExecutor(urlBuilder.buildGetUserInfoBatchUrl()).execute(JSON.toJSONString(dataParams)).getResponseAsObject(String.class);
                        //清空
                        openList.clear();
                        userInfosAll.addAll(JSON.parseArray(JSON.parseObject(userInfos).getString("user_info_list"), UserInfo.class));
                    }
                }
            }
            //最后一次执行
            dataParams.put("user_list", openList);
            userInfos = new RequestExecutor(urlBuilder.buildGetUserInfoBatchUrl()).execute(JSON.toJSONString(dataParams)).getResponseAsObject(String.class);
            userInfosAll.addAll(JSON.parseArray(JSON.parseObject(userInfos).getString("user_info_list"), UserInfo.class));
            Collections.sort(userInfosAll, (o1, o2) -> {
                //降序
                return o2.getSubscribeTime().compareTo(o1.getSubscribeTime());
            });
            return userInfosAll;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
        }
        return userInfosAll;
    }


    @Override
    public void updateRemark(String openId, String remark) throws WechatException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("openid", openId);
        dataParams.put("remark", remark);
        try {
            UserInfo s = getUserInfo(openId);
            if (StringUtils.isNotEmpty(s.getRemark()) && s.getRemark().equals(remark)){
                throw new WechatException("1", "请勿重复提交");
            } else {
                new RequestExecutor(urlBuilder.buildUpdateRemarkUrl()).execute(JSON.toJSONString(dataParams)).getResponseAsObject(String.class);
                s.setRemark(remark);
            }
            userRepository.save(s);
        } catch (WechatException | IOException e) {
            throw new WechatException("1", e.getMessage());
        }
    }

}
