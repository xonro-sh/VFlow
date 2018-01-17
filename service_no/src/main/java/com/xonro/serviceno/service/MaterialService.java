package com.xonro.serviceno.service;

import com.xonro.serviceno.bean.material.MaterialResult;
import com.xonro.serviceno.bean.material.NewsResponse;
import com.xonro.serviceno.exception.WechatException;

import java.io.File;

/**
 * 素材管理相关接口
 * @author Alex
 * @date 2018/1/17
 */
public interface MaterialService {
    /**
     * 新增临时素材接口
     * @param file 上传的文件
     * @param type 素材类型
     * @param fileName 文件名称
     * @return 返回结果
     */
    MaterialResult uploadTemMedia(byte[] file, String type, String fileName);

    /**
     * 获取临时素材接口
     * @param mediaId 媒体文件ID
     * @return 返回结果
     * @throws WechatException 异常
     */
    byte[] getTemMedia(String mediaId) throws WechatException;

    /**
     * 新增永久图文素材接口
     * @param newsResponse 图文素材请求数据模型
     * @return 返回media_id
     * @throws WechatException 异常
     */
    MaterialResult addNews(NewsResponse newsResponse) throws WechatException;

    /**
     * 上传图文消息内的图片获取URL
     * @param file 上传的文件
     * @param fileName 文件名称
     * @return url,可放置图文消息中使用
     */
    MaterialResult uploadImg(byte[] file, String fileName);

    /**
     * 新增永久视频素材
     * @param file 上传的文件
     * @param title 视频素材的标题
     * @param introduction 	视频素材的描述
     * @return MaterialResult
     */
    MaterialResult addVideo(File file, String title, String introduction);

    /**
     * 新增其他永久素材
     * @param file 上传的文件
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）和缩略图（thumb）
     * @param fileName 文件名称
     * @return MaterialResult
     */
    MaterialResult addOthers(byte[] file, String type, String fileName);
}
