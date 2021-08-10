package com.cykj.mapper;

import com.cykj.bean.Demand;
import com.cykj.bean.UnionInfo;
import com.cykj.bean.Works;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author guoquansen
 * @date 2021/8/7 11:21 上午
 */
@Mapper
public interface BusinessMapper {

    //作品列表
    List<Works> queryAllWorks(@Param("page") int page, @Param("pageSize") int pageSize);

    //作品总量
    int queryWorksTotal();

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

    //获取联盟总数
    int getUnionTotal();

}
