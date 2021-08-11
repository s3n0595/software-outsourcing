package com.cykj.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/11 14:01
 */
//public class HttpClientDownPage {
//    private static final String USER_AGENT="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36";
//    public static String sendGet(String url){
//        //1.生成httpclient，相当于该打开一个浏览器
//        CloseableHttpClient httpClient= HttpClients.createDefault();
//        //设置请求和传输时间
//        RequestConfig requestConfig=RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
//        CloseableHttpResponse response=null;
//        //2.创建get请求，相当于在浏览器地址栏输入 网址
//        HttpGet request=new HttpGet(url);
//        request.setHeader("User-Agent",USER_AGENT);
//        request.setConfig(requestConfig);
//        //3.执行get请求，相当于在输入地址栏后敲回车键
//        try {
//            response=httpClient.execute(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
