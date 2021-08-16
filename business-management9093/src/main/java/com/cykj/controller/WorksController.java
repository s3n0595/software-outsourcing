package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.ProviderPwd;
import com.cykj.bean.TradeWork;
import com.cykj.bean.Works;
import com.cykj.service.WorksService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/7/27 10:04
 * @desc:
 */
@Controller
@RequestMapping("/work")
@Slf4j
public class WorksController {
	@Autowired
    private WorksService worksService;

    @RequestMapping("/publish")
    @ResponseBody
    public String PublishWork(MultipartFile file, Works works) {
        System.out.println("==================================");
        System.out.println("*********文件上传*********");
        System.out.println("==================================");
        System.out.println(works);
        System.out.println(file.getOriginalFilename());
        if(worksService.publishWorks(file, works)) {
            return file.getOriginalFilename();
        } else {
            return "failed";
        }
    }
    @RequestMapping("/insert")
    @ResponseBody
    public int insertWork(Works works) {
        System.out.println("==================================");
        System.out.println("*********插入作品*********");
        System.out.println("==================================");
        System.out.println(works);
        return worksService.insertWorks(works);
    }
    @RequestMapping("/update")
    @ResponseBody
    public String updateWork(Works works) {
        System.out.println("==================================");
        System.out.println("*********更新ImgUrl*********");
        System.out.println("==================================");
        System.out.println(works);
       if(worksService.updateWorks(works)) {
           return "success";
       } else {
           return "failed";
       }
//        if(worksService.publishWorks(file, works)) {
//            return file.getOriginalFilename();
//        } else {
//            return "failed";
//        }
    }
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, String> uploadFile(MultipartFile[] file) {
        System.out.println("==================================");
        System.out.println("*********文件上传*********");
        System.out.println("==================================");
        System.out.println(file.length);
        for(int i = 0; i < file.length; i++) {
            System.out.println(file[i].getOriginalFilename());
        }
        return worksService.uploadFile(file);
//        return null;
    }

    @RequestMapping("/list")
    @ResponseBody
    @CrossOrigin
    public List<Works> WorksList(int providerId){
        System.out.println("==================================");
        System.out.println("*********作品集合*********");
        System.out.println("==================================");
       return worksService.queryMyWorks(providerId);
    }

    @RequestMapping("/img")
    @ResponseBody
    public void getImg(HttpServletResponse resp, String path){
        System.out.println("==================================");
        System.out.println("*********获取图片*********");
        System.out.println("==================================");
        System.out.println(path);
        try {
            File file = new File(path);
            FileInputStream fis;
            fis = new FileInputStream(file);

            long size = file.length();
            byte[] data = new byte[(int) size];
            fis.read(data, 0, (int) size);
            fis.close();
            resp.setContentType("image/png");
            OutputStream out = resp.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public CommonResult updatePwd(ProviderPwd providerPwd){
        log.info("***服务商修改密码***");
        int i = worksService.updatePwd(providerPwd.getProviderId(), providerPwd.getProviderPassword(), providerPwd.getPassword());
        if (i > 0) {
            return new CommonResult(200,"服务商密码修改成功",null);
        } else {
            return new CommonResult(400,"服务商密码修改失败",null);
        }
    }
    //小程序图片上传
    String filename;
    @RequestMapping("/imgUpLoad")
    @ResponseBody
    public String imgUpLoad(@RequestParam("file") MultipartFile file){
        System.out.println(file);
        System.out.println("上传图片");
        try {
            String realPath= ResourceUtils.getURL("classpath:").getPath()+"provider";
            System.out.println(realPath);
            File fileDir = new File(realPath);
            if (!fileDir.exists() && !fileDir.isDirectory()) {
                fileDir.mkdirs();
            }
            String uuid = UUID.randomUUID().toString();
             filename = uuid + '_' + file.getOriginalFilename();;//uuid+文件名
            System.out.println("文件名称："+filename);
            File saveFile = new File(realPath, filename);
            file.transferTo(saveFile);
            System.out.println("上传图片"+saveFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传成功";
    }
    //小程序发布作品
    @RequestMapping("/worksUpLoad")
    @ResponseBody
    public String worksUpLoad(String worksTitle,String worksTypeId,String worksDescribe,String worksPrice,String providerId,String worksAddress){
        System.out.println("小程序发布作品");
        Works works=new Works();
        works.setAuditStatus(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        works.setReleaseTime(sdf.format(new Date()));
        works.setWorksTypeId(Integer.valueOf(worksTypeId));
        works.setProviderId(Integer.valueOf(providerId));
        works.setWorksDescribe(worksDescribe);
        works.setWorksPrice(Double.valueOf(worksPrice));
        works.setWorksTitle(worksTitle);
        works.setWorksAddress(worksAddress);
        System.out.println("文件名字"+filename);
        works.setAnnexPath(filename);
        works.setImgUrl(filename);
        System.out.println(works);
        if(worksService.wechatAddWorks(works)>0){
            return "success";
        }else{
           return "false";
       }

    }

    //成功案例作品展示
    @RequestMapping("/success")
    @ResponseBody
    public CommonResult queryCaseList() {
        List<TradeWork> caseList = worksService.queryCaseList();
        log.warn("********成功案例**********"+caseList);
        return new CommonResult(200,"成功案例查询",caseList);
    }


}
