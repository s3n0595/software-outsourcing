package com.cykj.controller;


import com.alibaba.druid.sql.repository.SchemaObjectType;
import com.cykj.bean.*;
import com.cykj.service.EmpCenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("empcenter")
@CrossOrigin
@Slf4j
public class EmpCenterController {
    @Autowired
    private EmpCenterService empCenterService;

    //获取需求类型列表
    @RequestMapping("ckList")
    public List<DemandType> selCkList(){
        List<DemandType> cks=empCenterService.ckList();
        return cks;
    }
    //获取雇主发布的需求列表
    @RequestMapping("needList")
    public List<Demand> empNeeds(@RequestParam("employerId") int id){
        List<Demand> demands=empCenterService.nedds(id);
        for (int i=0;i<demands.size();i++){
            System.out.println(demands.get(i));
        }
        return demands;
    }

    //雇主添加需求
    @RequestMapping("file")
    public String getDemandFile(MultipartFile file, Demand demand){
        try {
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "EmpFolder";
            File fileDir = new File(realPath);
            if (!fileDir.exists() && !fileDir.isDirectory()) {
                fileDir.mkdirs();
            }
            String uuid = UUID.randomUUID().toString();
            String filename = uuid + '_' + file.getOriginalFilename();;//uuid+文件名
            File saveFile = new File(realPath, filename);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dateTime = df.format(date); // Formats a Date into a date/time string.
            demand.setEmployerId(1);
            demand.setReleaseTime(dateTime);
            demand.setAnnexPath(filename);
            int i = empCenterService.addNeed(demand);
            if (i > 0) {
                file.transferTo(saveFile);
                return "发布成功";
            } else {
                return "发布失败";
            }
          }catch (FileNotFoundException e) {
            e.printStackTrace();
          }catch (IOException e) {
            e.printStackTrace();
         }
        return "发布成功";
    }
    //雇主下载文件
    @RequestMapping("download")
    public HttpServletResponse downFile(HttpServletResponse response,HttpServletRequest request,String name){
        try {
            String fileName = request.getParameter("name").toString();
            // 要下载的文件的全路径名
            String filePath =ResourceUtils.getURL("classpath:").getPath() + "EmpFolder\\"+fileName;
            File file = new File(filePath);
            // 获取文件名
            String filename = file.getName().toString();
            //通过流把文件内容写入到客户端
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"ISO-8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //雇主修改需求
    @RequestMapping("editfile")
    public String editDemandFile(MultipartFile file, Demand demand){
        int i=empCenterService.editNeed(demand);
        if(i>0){
            return "修改成功";
        }else{
            return "修改失败";
        }
    }
    //删除需求记录
    @RequestMapping("delfile")
    public String delDemandFile(@RequestParam("demandId") int id){
        int i=empCenterService.delNeed(id);
        if(i>0){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }

    //修改雇主密码
    @RequestMapping("updatePwd")
    public CommonResult updatePwd(EmployerPwd employerPwd){
        log.info("***雇主修改密码***");
        int i = empCenterService.updatePwd(employerPwd.getEmployerId(), employerPwd.getEmployerPassword(),employerPwd.getPassword());
        if (i > 0) {
            return new CommonResult(200,"密码修改成功",null);
        } else {
            return new CommonResult(400,"密码修改失败",null);
        }
    }
}
