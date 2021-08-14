package com.cykj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/5 18:36
 */
@Mapper
public interface QworksMapper {
    List<Map<String, Object>> queryAllWork(@Param("count") int count,@Param("demandTypeName") String  demandTypeName,@Param("priceMin") int priceMin,@Param("priceMax") int priceMax,@Param("searchInfo") String searchInfo,@Param("sortType")String sortType);//获取所有作品列表
    int countProWork(@Param("providerId")int providerId);//计算单个服务商发布的作品
    Map<String,Object> workProInfo(int providerId);//获取服务商信息
}
