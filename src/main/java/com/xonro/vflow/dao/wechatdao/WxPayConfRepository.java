package com.xonro.vflow.dao.wechatdao;

import com.xonro.vflow.bean.wechat.WxPayConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
