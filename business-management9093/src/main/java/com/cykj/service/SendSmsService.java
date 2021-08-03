package com.cykj.service;

/**
 * @author guoquansen
 * @date 2021/8/2 11:21 下午
 */
public interface SendSmsService {

    boolean sendSms(String phoneNumber, String code);
}
