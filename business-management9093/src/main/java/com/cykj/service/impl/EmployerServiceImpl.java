package com.cykj.service.impl;

import com.cykj.mapper.EmployerMapper;
import com.cykj.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guoquansen
 * @date 2021/7/26 1:38 下午
 */
@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerMapper employerMapper;

    @Override
    public int addUser(EmployerAccount employerAccount) {
        return employerMapper.addUser(employerAccount);
    }
}
