package com.cykj.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.cykj.bean.Sms;
import com.cykj.service.SendSmsService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guoquansen
 * @date 2021/8/2 11:22 下午
 */
@Service
@Data
public class SendSmsServiceImpl implements SendSmsService {

    private static final Logger logger = LoggerFactory.getLogger(SendSmsServiceImpl.class);
    // 这里采用 注入的方式传递参数
//    @Value("${aliyun.accessKeyID}")
//    private String accessKeyID;
//    @Value("${aliyun.accessKeySecret}")
//    private String accessKeySecret;

    @Override
    public boolean sendSms(String phoneNumber, String code) {

        Sms sms = new Sms();
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", sms.getAccessKeyId(), sms.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", sms.getSignName());
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("TemplateCode", sms.getTemplateCode());

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);

        request.putQueryParameter("TemplateParam", JSON.toJSONString(params));

        try {
            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());  // 返回的消息
            logger.info(JSON.parseObject(response.getData(), Map.class).get("Message").toString());
            return response.getHttpResponse().isSuccess();

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return false;
    }
}
