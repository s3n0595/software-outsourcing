package com.cykj.controller;


import com.cykj.bean.Demand;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("empcenter")
@CrossOrigin
public class EmpCenterController {
    @RequestMapping("file")
    public String getDemandFile(MultipartFile[] file, Demand demand, HttpServletRequest req){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!"+file);
        System.out.println(demand);
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        String realPath = req.getServletContext().getRealPath("/EmpFolder");
//        System.out.println("服务器的路径地址为:"+realPath);
//        File fileDir = new File(realPath);
//        if (!fileDir.exists() && !fileDir.isDirectory()) {
//            fileDir.mkdirs();
//        }
//        try {
//            if (file != null && file.length > 0) {
//                for(int i = 0;i<file.length;i++){
//                    try {
//                        String uuid = UUID.randomUUID().toString();
//                        String filename=uuid+file[i].getOriginalFilename();;//uuid+文件名
//                        String storagePath = realPath+filename;
//                        System.out.println("上传的文件：" + file[i].getName() + "," + file[i].getContentType() + "," + filename
//                                +"，保存的路径为：" + storagePath);
//                        file[i].transferTo(new File(storagePath));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //前端可以通过状态码，判断文件是否上传成功
        return "文件上传成功";
    }

    @RequestMapping("cs")
    public String abc(@RequestParam("params") String params){
        System.out.println("==============="+params);
        return "abcde";
    }
}
