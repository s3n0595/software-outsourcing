package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.TradeWork;
import com.cykj.bean.Works;
import com.cykj.service.PortalService;
import com.cykj.vo.SuccessfulCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guoquansen
 * @date 2021/8/12 10:46 上午
 */
@Controller
@RequestMapping("/portal")
@Slf4j
public class PortalController {

    @Resource
    private PortalService portalService;

    //查询所有成功案例
    @RequestMapping("/caseList")
    @ResponseBody
    public CommonResult querySuccessCase() {
        log.info("******成功案例查询中******");
        List<TradeWork> allSuccessCase = portalService.queryAllSuccessCase();
        return new CommonResult(200,"案例查询成功",allSuccessCase);
    }

    //更改案例显示状态
    @RequestMapping("/showCase")
    @ResponseBody
    public CommonResult showCase(int tradeWorksId, int showCase) {
        log.info("******更改案例显示状态中******");
        int i = portalService.showCase(tradeWorksId, showCase);
        if (i > 0) {
            return new CommonResult(200,"状态更改成功",null);
        } else {
            return new CommonResult(400,"状态更改失败",null);
        }
    }

    //根据显示状态查询
    @RequestMapping("/isShow")
    @ResponseBody
    public CommonResult isShow(int showCase) {
        log.info("******显示状态查询中******");
        List<TradeWork> isShowList = portalService.queryIsShow(showCase);
        return new CommonResult(200,"显示状态列表查询成功",isShowList);
    }

    //批量删除案例
    @RequestMapping("/deleteCase")
    @ResponseBody
    public CommonResult deleteCase(int[] tradeWorksIds) {
        log.info("******批量删除中******");
        for(int tradeWorksId: tradeWorksIds) {
            portalService.deleteCase(tradeWorksId);
        }
        return new CommonResult(200,"批量删除成功",null);
    }

    //根据关键词获取作品id
    @RequestMapping("/searchCase")
    @ResponseBody
    public CommonResult queryWorksIdByTitle(String worksTitle) {
        log.info("******关键词******"+worksTitle);
        List<TradeWork> tradeWorkList = new ArrayList<>();
        List<Works> works = portalService.queryWorksIdByTitle(worksTitle);
        log.info("******作品******"+works);
        for (Works work : works) {
            TradeWork tradeWork = portalService.querySearchById(work.getWorksId());
            if (tradeWork != null) {
                tradeWorkList.add(tradeWork);
            }
        }
        log.info("******查出来结果结合作品******"+tradeWorkList);
        return new CommonResult(200,"显示状态列表查询成功",tradeWorkList);
    }


}
