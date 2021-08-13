package com.cykj.controller;

import com.cykj.bean.UsersBo;
import com.cykj.config.RestResponse;
import com.cykj.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/10 15:34
 */
@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/getCheckCode", method = RequestMethod.POST)
    public RestResponse getCheckCode(@RequestBody UsersBo usersBo){
        log.info("进入方法getCheckCode:"+usersBo.toString());
        RestResponse restResponse = new RestResponse();
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "一站式服务众包平台：您正在绑定邮箱信息，您的验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(usersBo.getEmail(), "一站式服务众包平台", message);
        }catch (Exception e){
//            restResponse.setData(e);
            return restResponse;
        }
        restResponse.setData(checkCode);
        return restResponse;
    }
}
