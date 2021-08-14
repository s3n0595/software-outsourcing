package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.ProviderPwd;
import com.cykj.bean.Works;
import com.cykj.service.WorksService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.List;
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


}
