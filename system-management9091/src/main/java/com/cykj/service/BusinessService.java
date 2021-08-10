package com.cykj.service;

import com.cykj.bean.Adviser;
import com.cykj.bean.UnionInfo;
import com.cykj.bean.Works;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author guoquansen
 * @date 2021/8/7 11:21 上午
 */
public interface BusinessService {

    List<Works> queryAllWorks();

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

    //获取顾问列表
    List<Adviser> queryAllAdviser();

    //顾问关键词
    List<Adviser> queryAdviserByProviderName(String providerName);

    //审核顾问
    int updateAdviser(int adviserId, int auditStatus);

    //根据顾问审核状态筛选
    List<Adviser> queryAdviserState(int auditStatus);

    //批量删除顾问
    int deleteAdviser(int adviserId);


}
