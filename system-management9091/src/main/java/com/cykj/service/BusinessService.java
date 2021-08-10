package com.cykj.service;

import com.cykj.bean.Demand;
import com.cykj.bean.UnionInfo;
import com.cykj.bean.Works;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author guoquansen
 * @date 2021/8/7 11:21 上午
 */
public interface BusinessService {

    List<Works> queryAllWorks(int page ,int pageSize);

    int queryWorksTotal();

    int updateStatus(int worksId, int auditStatus);

    List<Works> queryWorksTitle(String worksTitle);

    List<Works> queryWorksByAudit( int auditStatus);

    int deleteWorksList(int worksId);

    List<UnionInfo> queryAllUnion();

    //联盟审核
    int updateUnion(int unionId, int auditStatus);

    //联盟关键词搜索
    List<UnionInfo> queryUnionName(String unionName);

    //根据联盟审核状态查找
    List<UnionInfo> queryUnionByAudit(int auditStatus);

    //批量删除联盟
    int deleteUnion(int unionId);

    //获取联盟总数
    int getUnionTotal();

}
