package com.cykj.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/5 18:36
 */
@Mapper
public interface QworksMapper {
    List<Map<String, Object>> queryAllWork(int count);
}
