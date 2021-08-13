package com.cykj.mapper;

import com.cykj.bean.TradeWork;
import com.cykj.bean.Works;
import com.cykj.vo.SuccessfulCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author guoquansen
 * @date 2021/8/12 10:47 上午
 */
@Mapper
public interface PortalMapper {

    //查询所有成功案例
    List<TradeWork> queryAllSuccessCase();

    //是否显示案例
    int showCase(@Param("tradeWorksId") int tradeWorksId, @Param("showCase") int showCase);

    //根据显示状态查询
    List<TradeWork> queryIsShow(@Param("showCase") int showCase);

    //批量删除
    int deleteCase(@Param("tradeWorksId") int tradeWorksId);

    //查询作品id
    List<Works> queryWorksIdByTitle(@Param("worksTitle") String worksTitle);

    //根据关键词搜索
    TradeWork querySearchById(@Param("worksId") int worksId);



}
