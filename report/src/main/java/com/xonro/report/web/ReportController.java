package com.xonro.report.web;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Alex
 * @date 2018/2/11
 */
@EnableAutoConfiguration
@RestController
@RequestMapping(name = "report")
public class ReportController {

}
