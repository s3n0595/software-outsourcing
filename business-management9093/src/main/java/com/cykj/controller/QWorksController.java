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
    public List<Map<String, Object>> queryWorkdList(int count,@RequestParam("demandTypeName") String demandTypeName,@RequestParam("price") String price,@RequestParam("searchInfo") String searchInfo){
        int priceMin = -1;
        int priceMax = -1;
        switch (price){
            case "0-5K":
                priceMin=0;
                priceMax=5000;
                break;
            case "5K-1万":
                priceMin=5000;
                priceMax=10000;
                break;
            case "1万-5万":
                priceMin=10000;
                priceMax=50000;
                break;
            case "5万以上":
                priceMin=50000;
                break;
        }
        System.out.println("最小价格："+priceMin);
        System.out.println("最大价格："+priceMax);
        return qworksService.queryAllWork(count,demandTypeName,priceMin,priceMax,searchInfo);
    }

    @RequestMapping("selpro")
    public Map<String, Object> selproAllInfo(@RequestParam("proId") Integer proId){
       Map<String, Object> workproInfo=qworksService.workProInfo(proId);
       int count =qworksService.countProWork(proId);
       workproInfo.put("workCount",count);
       return workproInfo;
    }
}
