package com.cykj.controller;

import com.cykj.bean.Demand;
import com.cykj.service.impl.DemandServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/9 10:10
 * @desc:
 */
@RestController
@RequestMapping("/Demand")
public class DemandController {
    @Autowired
    private DemandServiceImp demandService;
    // 获取需求列表
    @GetMapping("/getDemandList")
    public List<Demand> getDemandList(){
        return demandService.getDemandList();
    }
    // 关键字
    @GetMapping("/getSearchDemand")
    public List<Demand> getSearchDemand(String employerName){
        return demandService.getSearchDemand(employerName);
    }
    // 通过审核状态查找
    @GetMapping("/getSearchState")
    public List<Demand> getSearchState(int demandStatus){
        return demandService.getSearchState(demandStatus);
    }
    // 修改需求状态
    @GetMapping("/updateDemandState")
    public String updateDemandState(int demandId,int demandStatus){
        System.out.println(demandId);
        System.out.println(demandStatus);
        int i = demandService.updateDemandState(demandId,demandStatus);
        return "操作成功";
    }
    // 删除需求
    @GetMapping("/deleteDemandList")
    @ResponseBody
    public String deleteDemandList(int[] demandIds){
        for (Integer demandId : demandIds) {
            demandService.deleteDemandList(demandId);
        }
        return "删除成功";
    }
}
