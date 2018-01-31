package com.xonro.weixinpay.dao;

import com.xonro.weixinpay.bean.WxPayConf;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex
 * @date 2018/1/25
 */
@Repository
public interface WxPayConfRepository extends JpaRepository<WxPayConf, Long>{
    /**
     * 根据id获取配置
     * @param id id
     * @return 结果模型
     */
    WxPayConf findById(String id);
}
