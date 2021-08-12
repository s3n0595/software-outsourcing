package com.cykj.service;

import com.cykj.bean.TradeWork;
import com.cykj.vo.SuccessfulCase;
import org.apache.ibatis.annotations.Param;

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

    //根据关键词搜索
    List<SuccessfulCase> queryByTitle(String worksTitle);



}
