package com.cykj.controller;

import com.aliyuncs.utils.StringUtils;
import com.cykj.bean.CommonResult;
import com.cykj.bean.Sms;
import com.cykj.service.SendSmsService;
import com.cykj.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;


/**
 * @author guoquansen
 * @date 2021/8/2 11:31 下午
 */

@Controller
@Slf4j
public class SendSmsController {

    @Autowired
    private SendSmsService sendSmsService;

    @Autowired
    private SmsService smsService;

    // 注入redis操作模板
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @PostMapping("/sendSms")
    @ResponseBody
    public CommonResult sendSms(@RequestParam("phoneNumber") String phoneNumber) {

        Sms sms = new Sms();
        for (Sms querySm : smsService.querySms()) {
            sms.setAccessKeyId(querySm.getAccessKeyId());
            sms.setAccessKeySecret(querySm.getAccessKeySecret());
            sms.setSignName(querySm.getSignName());
            sms.setTemplateCode(querySm.getTemplateCode());
        }

        log.info("手机号"+phoneNumber);
        // 获取到操作String的对象
        String code  = redisTemplate.opsForValue().get(phoneNumber);

        // 根据手机号进行查询
//        String phone = stringR.get(phoneNumber);

        // 如果手机号在redis中不存在的话才进行发送验证码操作
        if (StringUtils.isEmpty(code)) {
            // 生成6位随机数
            code = String.valueOf(Math.random()).substring(3, 9);
            // 将redisTemplate模板对象的key的序列化方式修改为new StringRedisSerializer
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            // 将phone当做key，将code当做value存进redis中，时间为5分钟
            redisTemplate.opsForValue().set(phoneNumber, code, 1440, TimeUnit.MINUTES);
            // 调用业务层接口 发送验证码
            boolean sendSmsFlag = sendSmsService.sendSms(phoneNumber, code);
            if (sendSmsFlag) {
                // 发送成功之后往redis中存入该手机号以及验证码 并设置超时时间 5 分钟
                log.info("验证码发送成功");
                return new CommonResult(200,"发送验证码到：" + phoneNumber + "成功! " + "Message:" + sendSmsFlag,null);
            } else {
                log.info("验证码发送失败");
                return new CommonResult(400,"验证码发送失败",null);
            }
        }
        return new CommonResult(400,"该手机号：" + phoneNumber + " 剩余：" + redisTemplate.getExpire(phoneNumber) + "秒后可再次进行发送！",null);
    }

    @PostMapping("/checkCode")
    @ResponseBody
    public CommonResult checkCode(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("code") String code) {

        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
        // 根据Key进行查询
        String redisCode = stringR.get(phoneNumber);
        log.info("前端输入的验证码-------->"+code);
        log.info("redis上保存的验证码----->"+redisCode);
        if (code.equals(redisCode)) {
            return new CommonResult(200,"短信验证成功",null);
        } else {
            return new CommonResult(400,"短信验证失败",null);
//            return redisCode == null ? "请先获取验证码在进行校验！" : "错误";
        }
    }
}
