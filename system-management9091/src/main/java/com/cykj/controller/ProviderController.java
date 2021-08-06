package com.cykj.controller;

import com.cykj.bean.ProviderAccount;
import com.cykj.bean.ProviderInfo;
import com.cykj.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/7/27 10:04
 * @desc:
 */
@Controller
@RequestMapping("/provider")
public class ProviderController {
	@Autowired
    private ProviderService providerService;

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> providerList(int page, String searchInfo,int pageSize){
        System.out.println("==================================");
        System.out.println("*********请求服务商列表参数*********");
        System.out.println("当前页 ： " + page);
        System.out.println("查询条件 : " + searchInfo);
        System.out.println("每页条数 : " + pageSize);
        System.out.println("==================================");
        return providerService.queryProvider(page, searchInfo, pageSize);
    }
    @GetMapping("/edit")
    @ResponseBody
    public boolean editProvider(ProviderAccount providerAccount, ProviderInfo providerInfo){
        System.out.println("==================================");
        System.out.println("*********请求服务商列表参数*********");
        System.out.println("服务商账号字段 ： " + providerAccount);
        System.out.println("服务商信息字段 ：" + providerInfo);
        System.out.println("==================================");
        return providerService.editProvider(providerAccount, providerInfo);
    }
    //小程序图片上传
    @RequestMapping("/imgUpLoad")
    @ResponseBody
    public String imgUpLoad(@RequestParam("file") MultipartFile file){
        System.out.println("上传图片");
        try {
            String realPath= ResourceUtils.getURL("classpath:").getPath()+"provider";
            System.out.println(realPath);
            File fileDir = new File(realPath);
            if (!fileDir.exists() && !fileDir.isDirectory()) {
                fileDir.mkdirs();
            }
            String uuid = UUID.randomUUID().toString();
            String filename = uuid + '_' + file.getOriginalFilename();;//uuid+文件名
            File saveFile = new File(realPath, filename);
            file.transferTo(saveFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传成功";
    }
}
