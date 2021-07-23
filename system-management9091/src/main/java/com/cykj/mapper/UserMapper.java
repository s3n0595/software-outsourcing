package com.cykj.mapper;

import com.cykj.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/7/23 4:43 下午
 * @desc:
 */
@Mapper
@Repository
public interface UserMapper {
    public List<User> queryall();
}
