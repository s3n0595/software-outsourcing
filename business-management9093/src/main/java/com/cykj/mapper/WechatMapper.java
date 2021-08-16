package com.cykj.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WechatMapper {
    int updatebal(@Param("employerId") int employerId, @Param("balance") String balance);
}
