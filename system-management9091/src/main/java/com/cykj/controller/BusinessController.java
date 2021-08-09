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

    //审核状态
    @RequestMapping("/updateStatus")
    @ResponseBody
    public CommonResult updateStatus(int worksId, int auditStatus) {
        log.info("********审核中*******");
        int approved = businessService.updateStatus(worksId, auditStatus);
        if (approved > 0) {
            return new CommonResult(200,"审核状态修改成功",approved);
        } else {
            return new CommonResult(400,"审核状态修改失败",null);
        }
    }

    //关键词搜索
    @RequestMapping("/search")
    @ResponseBody
    public CommonResult queryWorksTitle(String worksTitle) {
        log.info("********查询中*******");
        List<Works> queryWorksTitle = businessService.queryWorksTitle(worksTitle);
        return new CommonResult(200,"查询成功",queryWorksTitle);
    }

    //根据审核状态查找
    @RequestMapping("/state")
    @ResponseBody
    public CommonResult queryWorksByAudit(int auditStatus) {
        log.info("********查询中*******");
        List<Works> queryWorksByAudit = businessService.queryWorksByAudit(auditStatus);
        return new CommonResult(200,"审核状态查询成功",queryWorksByAudit);
    }

    //批量删除
    @RequestMapping("/deleteList")
    @ResponseBody
    public CommonResult deleteWorksList(int[] worksIds) {
        log.info("********删除中*******");
        for(int worksId: worksIds) {
            businessService.deleteWorksList(worksId);
        }
        return new CommonResult(200,"删除成功",null);
    }

}
