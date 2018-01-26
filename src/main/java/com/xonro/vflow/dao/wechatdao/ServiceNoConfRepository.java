package com.xonro.vflow.dao.wechatdao;

import com.xonro.vflow.bean.wechat.ServiceNoConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex
 * @date 2018/1/25
 */
@Repository
public interface ServiceNoConfRepository extends JpaRepository<ServiceNoConf, Long>{
    /**
     * 根据id获取
     * @param id id
     * @return 结果模型
     */
    ServiceNoConf findById(String id);
}
