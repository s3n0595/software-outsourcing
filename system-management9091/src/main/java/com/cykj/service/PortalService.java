package com.cykj.service;

import com.cykj.bean.TradeRecord;
import com.cykj.bean.TradeWork;
import com.cykj.bean.Works;
import com.cykj.vo.WeeksData;

import java.util.List;

/**
 * @author guoquansen
 * @date 2021/8/12 10:47 上午
 */
public interface PortalService {

    //查询所有成功案例
    List<TradeWork> queryAllSuccessCase();

    //是否显示案例
    int showCase(int tradeWorksId, int showCase);

    //根据显示状态查询
    List<TradeWork> queryIsShow(int showCase);

    //批量删除
    int deleteCase(int tradeWorksId);

    //查询作品id
    List<Works> queryWorksIdByTitle(String worksTitle);

    //根据关键词搜索
    TradeWork querySearchById(int worksId);

    //获取web网站数量
    int queryWeb();

    //获取app网站数量
    int queryApp();

    //获取app网站数量
    int queryWeChat();

    //获取app网站数量
    int queryHtml();

    //获取app网站数量
    int queryApplet();

    //获取app网站数量
    int queryOther();

    //获取网站作品数量
    int queryTotal();

    //获取网站每周交易量
    List<WeeksData> queryWeekData();



}
