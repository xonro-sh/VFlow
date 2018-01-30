package com.xonro.serviceno.dao;

import com.xonro.serviceno.bean.config.ServiceNoConf;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex
 * @date 2018/1/25
 */
@CacheConfig(cacheNames = "serviceno")
@Repository
public interface ServiceNoConfRepository extends JpaRepository<ServiceNoConf, Long> {
    /**
     * 根据id获取
     * @param id id
     * @return 结果模型
     */
    ServiceNoConf findById(String id);

    @Cacheable
    List<ServiceNoConf> findAll();
}
