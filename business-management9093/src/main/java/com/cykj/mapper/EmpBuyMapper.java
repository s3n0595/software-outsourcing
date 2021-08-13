package com.cykj.mapper;

import com.cykj.bean.CapitalFlow;
import com.cykj.bean.EmployerInfo;
import com.cykj.bean.TradeRetreat;
import com.cykj.bean.TradeWork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/8 14:10
 */
@Mapper
public interface EmpBuyMapper {
    int selBalance(Integer employerId);//查询雇主余额
    int editBalance(@Param("employerId") Integer employerId,@Param("balance") Integer balance);//购买成功修改雇主余额
    int addtradeWork(TradeWork tradeWork);//添加作品交易记录
    int addFlow(CapitalFlow capitalFlow);//添加流水表
    TradeWork oldtradeWork(TradeWork tradeWork);//查询用户是否购买过该作品
    List<Map<String, Object>> selbuyList(Integer employerId);//用户购买作品表
    int editState(TradeWork tradeWork);//雇主确认收货
    List<Map<String, Object>> selprobuyList(Integer providerId);//服务商个人中心交易作品
    int editproState(TradeWork tradeWork);//服务商修改交易作品进度
    EmployerInfo selEmpPwd(EmployerInfo employerInfo);//判断雇主交易密码是否正确
    int addtradeRetreat(TradeRetreat tradeRetreat);//雇主退货，新增退货表记录
    int editRetreat(@Param("tradeWorksId") int tradeWorksId,@Param("tradeStatus") int tradeStatus);//修改作品交易表状态为退货中
}
