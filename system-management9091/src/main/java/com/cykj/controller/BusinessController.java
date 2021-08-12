package com.cykj.controller;

import com.cykj.bean.*;
import com.cykj.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    public CommonResult queryAllWorks(){
        log.info("*******作品列表********");
        List<Works> worksList = businessService.queryAllWorks();
        return new CommonResult(200,"作品列表查询成功",worksList);
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

    //查询联盟记录
    @RequestMapping("/unionList")
    @ResponseBody
    public CommonResult queryUnionInfo(){
        log.info("********查询联盟记录中*******");
        List<UnionInfo> unionInfos = businessService.queryAllUnion();
        return new CommonResult(200,"查询联盟记录成功",unionInfos);
    }

    //联盟审核
    @RequestMapping("/union")
    @ResponseBody
    public CommonResult auditUnion(int unionId, int auditStatus) {
        log.info("********联盟审核中*******");
        int i = businessService.updateUnion(unionId, auditStatus);
        if (i > 0) {
            return new CommonResult(200,"联盟审核状态修改成功",i);
        } else {
            return new CommonResult(400,"联盟审核状态修改失败",null);
        }
    }

    //联盟关键词搜索
    @RequestMapping("/searchUnion")
    @ResponseBody
    public CommonResult queryUnionName(String unionName) {
        log.info("********联盟搜索中*******");
        List<UnionInfo> queryUnionName = businessService.queryUnionName(unionName);
        return new CommonResult(200,"联盟关键词搜索成功",queryUnionName);
    }

    //根据联盟审核状态搜索
    @RequestMapping("/unionState")
    @ResponseBody
    public CommonResult queryUnionState(int auditStatus) {
        log.info("********联盟审核状态搜索中*******");
        List<UnionInfo> queryUnionByAudit = businessService.queryUnionByAudit(auditStatus);
        return new CommonResult(200,"联盟审核状态搜索成功",queryUnionByAudit);
    }

    //批量删除联盟
    @RequestMapping("/deleteUnion")
    @ResponseBody
    public CommonResult deleteUnion(int[] unionIds){
        log.info("********批量删除联盟中*******");
        for (int unionId: unionIds) {
            businessService.deleteUnion(unionId);
        }
        return new CommonResult(200,"删除成功",null);
    }

    //获取顾问列表
    @RequestMapping("/adviser")
    @ResponseBody
    public CommonResult getAdviserList() {
        log.info("********获取顾问列表中*******");
        List<Adviser> adviserList = businessService.queryAllAdviser();
        return new CommonResult(200,"顾问列表",adviserList);
    }

    //顾问关键词搜索
    @RequestMapping("/searchAdviser")
    @ResponseBody
    public CommonResult searchAdviser(String providerName){
        log.info("********顾问关键词搜索中*******");
        List<Adviser> adviserList = new ArrayList<>();
        List<ProviderAccount> providerAccounts = businessService.queryIdByName(providerName);
        for (ProviderAccount account : providerAccounts) {
            Adviser adviser = businessService.queryAdviserByProviderId(account.getProviderId());
            if (adviser != null) {
                adviserList.add(adviser);
            }
        }
        return new CommonResult(200,"顾问关键词搜索列表",adviserList);
    }

    //顾问审核
    @RequestMapping("/adviserAudit")
    @ResponseBody
    public CommonResult adviserAudit(int adviserId, int auditStatus) {
        int i = businessService.updateAdviser(adviserId, auditStatus);
        if (i > 0) {
            return new CommonResult(200,"顾问审核状态修改成功",i);
        } else {
            return new CommonResult(400,"顾问审核状态修改失败",null);
        }
    }

    //根据审核状态筛选
    @RequestMapping("/adviserState")
    @ResponseBody
    public CommonResult adviserState(int auditStatus) {
        List<Adviser> adviserList = businessService.queryAdviserState(auditStatus);
        return new CommonResult(200,"顾问审核状态列表",adviserList);
    }

    //批量删除
    @RequestMapping("/deleteAdviser")
    @ResponseBody
    public CommonResult deleteAdviser(int[] adviserIds) {
        for(int adviserId : adviserIds) {
            businessService.deleteAdviser(adviserId);
        }
        return new CommonResult(200,"删除成功",null);
    }


}
