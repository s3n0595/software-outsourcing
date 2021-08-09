package com.cykj.service;

import com.cykj.bean.Demand;
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
}
