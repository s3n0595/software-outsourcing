package com.cykj.mapper;

import com.cykj.bean.Adviser;
import com.cykj.bean.ProviderAccount;
import com.cykj.bean.UnionInfo;
import com.cykj.bean.Works;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author guoquansen
 * @date 2021/8/7 11:21 上午
 */
@Mapper
public interface BusinessMapper {

    //作品列表
    List<Works> queryAllWorks();

    //作品审核
    int updateStatus(@Param("worksId") int worksId,@Param("auditStatus") int auditStatus);

    //关键词搜索
    List<Works> queryWorksTitle(@Param("worksTitle") String worksTitle);

    //根据审核状态查找
    List<Works> queryWorksByAudit(@Param("auditStatus") int auditStatus);

    //批量删除作品
    int deleteWorksList(@Param("worksId") int worksId);

    //联盟列表
    List<UnionInfo> queryAllUnion();

    //联盟审核
    int updateUnion(@Param("unionId") int unionId,@Param("auditStatus") int auditStatus);

    //联盟关键词搜索
    List<UnionInfo> queryUnionName(@Param("unionName") String unionName);

    //根据联盟审核状态查找
    List<UnionInfo> queryUnionByAudit(@Param("auditStatus") int auditStatus);

    //批量删除联盟
    int deleteUnion(@Param("unionId") int unionId);

    //获取顾问列表
    List<Adviser> queryAllAdviser();

    //获取顾问关键词搜索出的id
    List<ProviderAccount> queryIdByName(@Param("providerName") String providerName);

    //顾问关键词
    Adviser queryAdviserByProviderId(@Param("providerId") int providerId);

    //审核顾问
    int updateAdviser(@Param("adviserId") int adviserId, @Param("auditStatus") int auditStatus);

    //根据顾问审核状态筛选
    List<Adviser> queryAdviserState(@Param("auditStatus") int auditStatus);

    //批量删除顾问
    int deleteAdviser(@Param("adviserId") int adviserId);

}
