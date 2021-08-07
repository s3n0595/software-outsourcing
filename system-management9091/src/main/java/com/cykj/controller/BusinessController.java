package com.cykj.controller;

import com.cykj.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author guoquansen
 * @date 2021/8/7 11:20 上午
 */
@Controller
@RequestMapping("/business")
@Slf4j
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping ("/demand")
    @ResponseBody
    public List<Map<String, Object>> queryAllDemand(int count){
        log.info("***count***"+count);
        return businessService.queryAllDemand(count);
    }

}
