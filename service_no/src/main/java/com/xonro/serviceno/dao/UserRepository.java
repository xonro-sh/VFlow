package com.xonro.serviceno.dao;

import com.xonro.serviceno.bean.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户数据仓储
 * @author louie
 * @date 2018-1-12
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo,Long> {
}
