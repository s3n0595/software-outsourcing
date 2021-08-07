package com.cykj.controller;

import com.cykj.service.QworksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/5 18:03
 * @desc:前台作品相关业务
 */
@RestController
@RequestMapping("qworks")
@CrossOrigin
@Slf4j
public class QWorksController {
    @Autowired
    private QworksService qworksService;
    @RequestMapping("worksList")
    public List<Map<String, Object>> queryWorkdList(int count){
        return qworksService.queryAllWork(count);
    }

    @RequestMapping("selpro")
    public Map<String, Object> selproAllInfo(@RequestParam("proId") Integer proId){
       Map<String, Object> workproInfo=qworksService.workProInfo(proId);
       int count =qworksService.countProWork(proId);
       workproInfo.put("workCount",count);
       return workproInfo;
    }
}
