package com.cykj.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author guoquansen
 * @date 2021/8/7 11:21 上午
 */
@Mapper
public interface BusinessMapper {

    List<Map<String, Object> > queryAllDemand(int count);
}
