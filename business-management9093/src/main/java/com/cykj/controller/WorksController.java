package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.ProviderPwd;
import com.cykj.bean.Works;
import com.cykj.service.WorksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    @CrossOrigin
    public List<Works> WorksList(){
        System.out.println("==================================");
        System.out.println("*********作品集合*********");
        System.out.println("==================================");
       return worksService.queryAllWorks();
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
        System.out.println(works);
        if(worksService.wechatAddWorks(works)>0){
            return "success";
        }else{
           return "false";
       }

    }


}
