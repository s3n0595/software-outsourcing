package com.cykj.mapper;

import com.cykj.bean.Sms;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author guoquansen
 * @date 2021/8/3 9:35 下午
 */
@Mapper
public interface SmsMapper {

    List<Sms> querySms();
}
