package com.xonro.report.service.impl;

import com.xonro.report.bean.BaseResponse;
import com.xonro.report.bean.ReportTheme;
import com.xonro.report.bean.TableResponse;
import com.xonro.report.dao.ReportThemeRepository;
import com.xonro.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex
 * @date 2018/2/11
 */
@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportThemeRepository reportThemeRepository;


    @Override
    public TableResponse getReportTheme() {
        List<ReportTheme> reportThemes = reportThemeRepository.findAll();
        return new TableResponse(0, "", reportThemes.size(), reportThemes);
    }

    @Override
    public BaseResponse updateReportTheme(ReportTheme reportTheme) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            reportThemeRepository.save(reportTheme);
        } catch (Exception e) {
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }
}
