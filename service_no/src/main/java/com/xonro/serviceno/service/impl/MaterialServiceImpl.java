package com.xonro.serviceno.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.serviceno.bean.material.Article;
import com.xonro.serviceno.bean.material.MaterialCountResult;
import com.xonro.serviceno.bean.material.MaterialResult;
import com.xonro.serviceno.bean.material.NewsResponse;
import com.xonro.serviceno.enums.WechatEnums;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.MaterialService;
import com.xonro.serviceno.web.RequestExecutor;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
    public MaterialResult uploadTemMedia(@NotNull File file, @NotNull String type) {
        try {
            return new RequestExecutor(urlBuilder.buildUploadTemMediaUrl(type))
                    .upload(file.getName(), FileUtils.readFileToByteArray(file))
                    .getResponseAsObject(MaterialResult.class);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 获取临时素材接口
     *
     * @param mediaId 媒体文件ID
     * @return 返回结果
     * @throws WechatException 异常
     */
    @Override
    public byte[] getTemMedia(@NotNull String mediaId) throws WechatException {
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
    public MaterialResult addNews(@NotNull NewsResponse newsResponse) throws WechatException {
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
    public MaterialResult uploadImg(@NotNull File file) {
        try {
            return new RequestExecutor(urlBuilder.buildUpLoadImg())
                    .upload(file.getName(), FileUtils.readFileToByteArray(file))
                    .getResponseAsObject(MaterialResult.class);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
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
    public MaterialResult addVideo(@NotNull File file, @NotNull String title, @NotNull String introduction) {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("title", title);
        dataParams.put("introduction", introduction);
        Map<String, String> dataMap = new HashMap<>(1);
        dataMap.put("description", JSON.toJSONString(dataParams));
        MaterialResult materialResult;
        try {
            materialResult = new RequestExecutor(urlBuilder.buildMaterialAddUrl(WechatEnums.MATERIAL_VIDEO.getValue())).
                    uploadVideo(file.getName(), FileUtils.readFileToByteArray(file), dataMap).
                    getResponseAsObject(MaterialResult.class);
            return materialResult;
        } catch (IOException | WechatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新增其他永久素材
     *
     * @param file 上传的文件
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）和缩略图（thumb）
     * @return MaterialResult
     */
    @Override
    public MaterialResult addOthers(@NotNull File file, @NotNull String type) {
        try {
            return new RequestExecutor(urlBuilder.buildMaterialAddUrl(type))
                    .upload(file.getName(), FileUtils.readFileToByteArray(file))
                    .getResponseAsObject(MaterialResult.class);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 删除永久素材
     *
     * @param mediaId 要获取的素材的media_id
     * @return 删除结果
     * @throws WechatException 异常
     */
    @Override
    public MaterialResult delete(@NotNull String mediaId) throws WechatException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("media_id", mediaId);
        MaterialResult materialResult;
        try {
            materialResult = new RequestExecutor(urlBuilder.buildMaterialDelUrl())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(MaterialResult.class);
            return materialResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("001", "删除永久素材失败！");
        }
    }

    /**
     * 修改永久图文素材
     *
     * @param mediaId      要修改的素材的media_id
     * @param index        要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
     * @param article 图文素材请求数据模型
     * @return 修改结果
     * @throws WechatException 异常
     */
    @Override
    public MaterialResult updateNews(@NotNull String mediaId, @NotNull String index, @NotNull Article article) throws WechatException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("media_id", mediaId);
        dataParams.put("index", mediaId);
        dataParams.put("articles", article);
        MaterialResult materialResult;
        try {
            materialResult = new RequestExecutor(urlBuilder.buildMaterialUpdateNewsUrl())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(MaterialResult.class);
            return materialResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("002", "修改永久图文素材失败！");
        }
    }

    /**
     * 获取素材总数
     *
     * @return 素材数量模型
     * @throws WechatException 异常
     */
    @Override
    public MaterialCountResult getMaterialCount() throws WechatException {
        MaterialCountResult materialCountResult;
        try {
            materialCountResult = new RequestExecutor(urlBuilder.buildMaterialCountUrl())
                    .execute()
                    .getResponseAsObject(MaterialCountResult.class);
            return materialCountResult;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("003", "获取素材总数！");
        }
    }

    /**
     * 获取素材列表
     *
     * @param type   素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  返回素材的数量，取值在1到20之间
     * @return 素材信息json
     * @throws WechatException 异常
     */
    @Override
    public String getMaterialBatch(@NotNull String type, @NotNull Integer offset, @NotNull Integer count) throws WechatException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("type", type);
        dataParams.put("offset", offset);
        dataParams.put("count", count);
        String result;
        try {
            result = new RequestExecutor(urlBuilder.buildGetMaterialBatch())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(String.class);
            return result;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("004", "获取素材列表失败！");
        }
    }
}
