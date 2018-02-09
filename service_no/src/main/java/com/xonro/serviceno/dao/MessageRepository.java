package com.xonro.serviceno.dao;

import com.xonro.serviceno.bean.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex
 * @date 2018/2/6
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
    List<Message> findByType(String type);
}
