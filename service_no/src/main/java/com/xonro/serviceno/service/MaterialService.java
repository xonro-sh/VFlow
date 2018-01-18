package com.xonro.serviceno.service;

import com.xonro.serviceno.bean.material.Article;
import com.xonro.serviceno.bean.material.MaterialCountResult;
import com.xonro.serviceno.bean.material.MaterialResult;
import com.xonro.serviceno.bean.material.NewsResponse;
import com.xonro.serviceno.exception.WechatException;

import javax.validation.constraints.NotNull;
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
     * @return 返回结果
     */
    MaterialResult uploadTemMedia(@NotNull File file, @NotNull String type);

    /**
     * 获取临时素材接口
     * @param mediaId 媒体文件ID
     * @return 返回结果
     * @throws WechatException 异常
     */
    byte[] getTemMedia(@NotNull String mediaId) throws WechatException;

    /**
     * 新增永久图文素材接口
     * @param newsResponse 图文素材请求数据模型
     * @return 返回media_id
     * @throws WechatException 异常
     */
    MaterialResult addNews(@NotNull NewsResponse newsResponse) throws WechatException;

    /**
     * 上传图文消息内的图片获取URL
     * @param file 上传的文件
     * @return url,可放置图文消息中使用
     */
    MaterialResult uploadImg(@NotNull File file);

    /**
     * 新增永久视频素材
     * @param file 上传的文件
     * @param title 视频素材的标题
     * @param introduction 	视频素材的描述
     * @return MaterialResult
     */
    MaterialResult addVideo(@NotNull File file, @NotNull String title, @NotNull String introduction);

    /**
     * 新增其他永久素材
     * @param file 上传的文件
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）和缩略图（thumb）
     * @return MaterialResult
     */
    MaterialResult addOthers(@NotNull File file, @NotNull String type);

    /**
     * 删除永久素材
     * @param mediaId 要删除的素材的media_id
     * @return 删除结果
     * @throws WechatException 异常
     */
    MaterialResult delete(@NotNull String mediaId) throws WechatException;

    /**
     * 修改永久图文素材
     * @param mediaId 要修改的素材的media_id
     * @param index 要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
     * @param article 图文素材请求数据模型
     * @return 修改结果
     * @throws WechatException 异常
     */
    MaterialResult updateNews(@NotNull String mediaId, @NotNull String index, @NotNull Article article) throws WechatException;

    /**
     * 获取素材总数
     * @return 素材数量模型
     * @throws WechatException 异常
     */
    MaterialCountResult getMaterialCount() throws WechatException;

    /**
     * 获取素材列表
     * @param type 	素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count 返回素材的数量，取值在1到20之间
     * @return 素材信息json
     * @throws WechatException 异常
     */
    String getMaterialBatch(@NotNull String type, @NotNull Integer offset, @NotNull Integer count) throws WechatException;
}
