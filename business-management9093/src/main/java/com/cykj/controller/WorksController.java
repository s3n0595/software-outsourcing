package com.cykj.controller;

import com.cykj.bean.Works;
import com.cykj.service.ProviderService;
import com.cykj.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
}
