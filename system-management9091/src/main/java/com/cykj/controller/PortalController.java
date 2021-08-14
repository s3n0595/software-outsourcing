package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.TradeRecord;
import com.cykj.bean.TradeWork;
import com.cykj.bean.Works;
import com.cykj.service.PortalService;
import com.cykj.vo.WeeksData;
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

    //获取web网站作品数量
    @RequestMapping("/web")
    @ResponseBody
    public CommonResult queryWeb() {
        int web = portalService.queryWeb();
        int total = portalService.queryTotal();
        double webPer = Double.valueOf(web)/Double.valueOf(total);
        webPer = (double)Math.round(webPer * 100) / 100;
        return new CommonResult(200,"web网站数量",webPer);
    }

    //获取app网站作品数量
    @RequestMapping("/app")
    @ResponseBody
    public CommonResult queryApp() {
        int app = portalService.queryApp();
        int total = portalService.queryTotal();
        double appPer = Double.valueOf(app)/Double.valueOf(total);
        appPer = (double)Math.round(appPer * 100) / 100;
        return new CommonResult(200,"app网站数量",appPer);
    }

    //获取微信网站作品数量
    @RequestMapping("/weChat")
    @ResponseBody
    public CommonResult queryWeChat() {
        int weChat = portalService.queryWeChat();
        int total = portalService.queryTotal();
        double weChatPer = Double.valueOf(weChat)/Double.valueOf(total);
        weChatPer = (double)Math.round(weChatPer * 100) / 100;
        return new CommonResult(200,"weChat网站数量",weChatPer);
    }

    //获取html网站作品数量
    @RequestMapping("/html")
    @ResponseBody
    public CommonResult queryHtml() {
        int html = portalService.queryHtml();
        int total = portalService.queryTotal();
        double htmlPer = Double.valueOf(html)/Double.valueOf(total);
        htmlPer = (double)Math.round(htmlPer * 100) / 100;
        return new CommonResult(200,"html网站数量",htmlPer);
    }

    //获取applet网站作品数量
    @RequestMapping("/applet")
    @ResponseBody
    public CommonResult queryApplet() {
        int applet = portalService.queryApplet();
        int total = portalService.queryTotal();
        double appletPer = Double.valueOf(applet)/Double.valueOf(total);
        appletPer = (double)Math.round(appletPer * 100) / 100;
        return new CommonResult(200,"applet网站数量",appletPer);
    }

    //获取other网站作品数量
    @RequestMapping("/other")
    @ResponseBody
    public CommonResult queryOther() {
        int other = portalService.queryOther();
        int total = portalService.queryTotal();
        double otherPer = Double.valueOf(other)/Double.valueOf(total);
        otherPer = (double)Math.round(otherPer * 100) / 100;
        return new CommonResult(200,"other网站数量",otherPer);
    }

    //获取网站作品数量
    @RequestMapping("/total")
    @ResponseBody
    public CommonResult queryTotal() {
        int total = portalService.queryTotal();
        return new CommonResult(200,"other网站数量",total);
    }

    //获取网站作品数量
    @RequestMapping("/week")
    @ResponseBody
    public CommonResult queryWeekData() {
        List<WeeksData> tradeRecords = portalService.queryWeekData();
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        log.info("*************************"+tradeRecords);
        return new CommonResult(200,"other网站数量",tradeRecords);
    }

}
