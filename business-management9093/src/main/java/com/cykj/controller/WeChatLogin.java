package com.cykj.controller;

//public class WeChatLogin {
//    package com.xx.action;

import java.util.Map;

import com.cykj.bean.WeChatSession;
import org.springframework.http.HttpMethod;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;


import com.google.gson.Gson;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

//import com.hp.bean.WeChatAppLoginReq;

//import com.hp.bean.WeChatSession;

//import com.opensymphony.xwork2.ActionSupport;

@Component
public class WeChatLogin {


    private static final long serialVersionUID = 1L;

    private static final String APPID = "wx83c26a7617ffb32a";

    private static final String SECRET = "876bfff59878863d1b0508d63c66f724";


    //获取凭证校检接口
    public ResponseEntity login(String code) {
//微信的接口
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID +
                "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
//进行网络请求,访问url接口
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        System.out.println(responseEntity);
        //根据返回值进行后续操作
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            Gson gson = new Gson();
//解析从微信服务器获得的openid和session_key;
            WeChatSession weChatSession = gson.fromJson(sessionData, WeChatSession.class);
//获取用户的唯一标识
            String openid = weChatSession.getOpenid();
//获取会话秘钥
            String session_key = weChatSession.getSession_key();
//下面就可以写自己的业务代码了

//最后要返回一个自定义的登录态,用来做后续数据传输的验证

            return responseEntity;
        }

        return null;


    }

}
