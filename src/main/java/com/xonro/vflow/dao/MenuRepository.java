package com.xonro.vflow.dao;

import com.xonro.vflow.bean.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alex
 * @date 2018/1/23
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{
    /**
     * 分页查找所有
     * @param pageable
     * @return
     */
    @Override
    Page<Menu> findAll(Pageable pageable);

    /**
     * 根据ID查找信息
     * @param id id
     * @return
     */
    Menu findById(String id);

    /**
     * 根据父级id查找信息
     * @param pNo
     * @return
     */
    List<Menu> findByPNoOrderByItemNoDesc(String pNo);

    Menu findByItemNo(String itemNo);

}
