package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.Demand;
import com.cykj.bean.Works;
import com.cykj.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author guoquansen
 * @date 2021/8/7 11:20 上午
 */
@Controller
@RequestMapping("/audit")
@Slf4j
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping("works")
    @ResponseBody
    //获取作品列表
    public CommonResult queryAllWorks(int page, int pageSize){
        log.info("*******作品列表********");
        log.info("*******作品列表********");
        log.info("*******作品列表********");
        log.info("*******作品列表********");
        List<Works> worksList = businessService.queryAllWorks(page,pageSize);
        return new CommonResult(200,"作品列表查询成功",worksList);
    }

    //获取总数
    @RequestMapping("/total")
    @ResponseBody
    public CommonResult queryTotal(){
        int worksTotal = businessService.queryWorksTotal();
        log.info("******作品总数*****"+worksTotal);
        log.info("******作品总数*****"+worksTotal);
        log.info("******作品总数*****"+worksTotal);
        log.info("******作品总数*****"+worksTotal);
        return new CommonResult(200,"页数获取成功",worksTotal);
    }

}
