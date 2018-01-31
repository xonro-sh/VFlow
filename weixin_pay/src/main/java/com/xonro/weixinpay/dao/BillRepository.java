package com.xonro.weixinpay.dao;

import com.xonro.weixinpay.bean.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex
 * @date 2018/1/31
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{
    Page<Bill> findByBillDate(String billDate, Pageable pageable);
    @Override
    Page<Bill> findAll(Pageable pageable);
}
