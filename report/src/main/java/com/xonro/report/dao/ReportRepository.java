package com.xonro.report.dao;

import com.xonro.report.bean.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex
 * @date 2018/2/11
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{
}
