package com.cykj.mapper;

import com.cykj.bean.Demand;
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
}
