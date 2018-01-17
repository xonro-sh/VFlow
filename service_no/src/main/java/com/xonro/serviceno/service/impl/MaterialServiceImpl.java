package com.xonro.serviceno.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.serviceno.bean.material.MaterialResult;
import com.xonro.serviceno.bean.material.NewsResponse;
import com.xonro.serviceno.enums.WechatEnums;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.MaterialService;
import com.xonro.serviceno.web.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author Alex
 * @date 2018/1/17
 */
@Service
public class MaterialServiceImpl implements MaterialService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UrlBuilder urlBuilder;
    /**
     * 新增临时素材接口
     *
     * @param file 上传的文件
     * @param type 素材类型
     * @return 返回结果
     */
    @Override
    public MaterialResult uploadTemMedia(File file, String type) {
        MaterialResult materialResult;
        materialResult = new RequestExecutor(urlBuilder.buildUploadTemMediaUrl(type)).postFile(file,MaterialResult.class);
        return materialResult;
    }

    /**
     * 获取临时素材接口
     *
     * @param mediaId 媒体文件ID
     * @return 返回结果
     * @throws WechatException 异常
     */
    @Override
    public byte[] getTemMedia(String mediaId) throws WechatException {
        byte[] materialResult;
        try {
            materialResult = new RequestExecutor(urlBuilder.buildGetTemMediaUrl(mediaId))
                    .downloadFile();
            return materialResult;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("001", "获取临时素材失败，请检查媒体文件ID是否有效！");
        }
    }

    /**
     * 新增永久图文素材接口
     *
     * @param newsResponse 图文素材请求数据模型
     * @return 返回media_id
     * @throws WechatException 异常
     */
    @Override
    public MaterialResult addNews(NewsResponse newsResponse) throws WechatException {
        MaterialResult materialResult;
        try {
            materialResult = new RequestExecutor(urlBuilder.buildMaterialAddNewsUrl())
                    .execute(JSON.toJSONString(newsResponse))
                    .getResponseAsObject(MaterialResult.class);
            return materialResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("002", "新增永久图文素材失败");
        }
    }

    /**
     * 上传图文消息内的图片获取URL
     *
     * @param file 上传的文件
     * @return url, 可放置图文消息中使用
     */
    @Override
    public MaterialResult uploadImg(File file) {
        MaterialResult materialResult;
        materialResult = new RequestExecutor(urlBuilder.buildUpLoadImg()).postFile(file,MaterialResult.class);
        return materialResult;
    }

    /**
     * 新增永久视频素材
     *
     * @param file         上传的文件
     * @param title        视频素材的标题
     * @param introduction 视频素材的描述
     * @return MaterialResult
     */
    @Override
    public MaterialResult addVideo(File file, String title, String introduction) {
        MaterialResult materialResult;
        materialResult = new RequestExecutor(urlBuilder.buildMaterialAddUrl(WechatEnums.MATERIAL_VIDEO.getValue())).postFile(file,title ,introduction,MaterialResult.class);
        return materialResult;
    }

    /**
     * 新增其他永久素材
     *
     * @param file 上传的文件
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）和缩略图（thumb）
     * @return MaterialResult
     */
    @Override
    public MaterialResult addOthers(File file, String type) {
        MaterialResult materialResult;
        materialResult = new RequestExecutor(urlBuilder.buildMaterialAddUrl(type)).postFile(file,MaterialResult.class);
        return materialResult;
    }
}
