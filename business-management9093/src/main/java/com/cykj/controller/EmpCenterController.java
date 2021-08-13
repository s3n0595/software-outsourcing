package com.cykj.controller;


import com.cykj.bean.*;
import com.cykj.config.HtmlParseUtil;
import com.cykj.service.EmpCenterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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
import java.util.Map;
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
        if(file!=null){
            try {
                String realPath = ResourceUtils.getURL("classpath:").getPath() + "EmpFolder";
                File fileDir = new File(realPath);
                if (!fileDir.exists() && !fileDir.isDirectory()) {
                    fileDir.mkdirs();
                }
                String uuid = UUID.randomUUID().toString();
                String filename = uuid + '_' + file.getOriginalFilename();;//uuid+文件名
                File saveFile = new File(realPath, filename);
                file.transferTo(saveFile);
                demand.setAnnexPath(filename);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateTime = df.format(date); // Formats a Date into a date/time string.
        demand.setReleaseTime(dateTime);
        int i = empCenterService.addNeed(demand);
        if (i > 0) {
            return "发布成功";
        } else {
            return "发布失败";
        }
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
    //查询雇主账户余额
    @RequestMapping("selbalance")
    public int empBalance(@RequestParam("employerId") int employerId){
        int i=empCenterService.selempBalance(employerId);
        return i;
    }

    //修改雇主邮箱
    @RequestMapping("editEmail")
    public int empEmail(EmployerInfo employerInfo){
       int i=empCenterService.updateEmpEmail(employerInfo);
       if(i>0){
           return 1;
       }else {
           return 0;
       }
    }

    //修改服务商邮箱
    @RequestMapping("editproEmail")
    public int proEmail(ProviderInfo providerInfo){
        int i=empCenterService.updateProEmail(providerInfo);
        if(i>0){
            return 1;
        }else {
            return 0;
        }
    }

    //雇主、服务商修改账户头像
    @RequestMapping("uploadhead")
    public int editHead(MultipartFile file, @RequestParam("Id") int Id,@RequestParam("role") String role){
        try {
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "EmpFolder";
            File fileDir = new File(realPath);
            if (!fileDir.exists() && !fileDir.isDirectory()) {
                fileDir.mkdirs();
            }
            String headName=file.getOriginalFilename();
            File saveFile = new File(realPath, headName);
            file.transferTo(saveFile);
            if(role.equals("emp")){
                empCenterService.updateHead(Id,headName);
            }else{
                empCenterService.updateproHead(Id,headName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    //个人中心基本信息--查询
    @RequestMapping("centerInfo")
    public Map<String,Object> selempInfo(int employerId){
        return empCenterService.selempInfo(employerId);
    }

    @RequestMapping("procenterInfo")
    public Map<String,Object> selproInfo(int providerId){
        return empCenterService.selproInfo(providerId);
    }

    //雇主个人中心修改名称
    @RequestMapping("editEmpName")
    public int editEName(EmployerAccount employerAccount){
        int i=empCenterService.updateEmpName(employerAccount);
        if(i>0){
            return 1;
        }else{
            return 0;
        }
    }
    //服务商个人中心修改名称
    @RequestMapping("editProName")
    public int editPName(ProviderAccount providerAccount){
        int i=empCenterService.updateProName(providerAccount);
        if(i>0){
            return 1;
        }else{
            return 0;
        }
    }
    //查看信用分明细
    @RequestMapping("redCredit")
    public List<CreditDetails> readCredit(CreditDetails creditDetails){
        return empCenterService.selCredit(creditDetails);
    }
    //查询服务商账户余额
    @RequestMapping("selprobalance")
    public int proBalance(@RequestParam("providerId") int providerId){
        return empCenterService.selproBalance(providerId);
    }
    //雇主、服务商查询是否存在交易密码
    @RequestMapping("selPwd")
    public Map<String,Object> seletPwd(@RequestParam("userID") int userId,@RequestParam("role") String role){
        if(role.equals("emp")){
            return empCenterService.selEmpInfo(userId);
        }else{
            return empCenterService.selProInfo(userId);
        }
    }
    //雇主、服务商首次设置交易密码
    @RequestMapping("setPwd")
    public int setPwd(@RequestParam("userID") int userId,@RequestParam("role") String role,@RequestParam("transactionPwd") int transactionPwd){
       if(role.equals("emp")){
           empCenterService.empSetPwd(userId,transactionPwd);

       }else{
           empCenterService.proSetPwd(userId,transactionPwd);
       }
       return 1;
    }
    //雇主、服务商修改交易密码
    @RequestMapping("editPwd")
    public int editPwd(@RequestParam("userID") int userId,@RequestParam("role") String role,@RequestParam("oldpwd") int oldpwd,@RequestParam("opwd") int opwd){
        if(role.equals("emp")){
            int i=empCenterService.empEditPwd(userId,oldpwd,opwd);
            if(i>0){
                return 1;
            }else{return 0;}
        }else{
            int i=empCenterService.proEditPwd(userId,oldpwd,opwd);
            if(i>0){
                return 1;
            }else{return 0;}
        }
    }

    @RequestMapping("flow")//雇主流水明细表
    public List<Map<String, Object>> allFlow(CapitalFlow capitalFlow){
        return empCenterService.selempAllFlow(capitalFlow);
    }

    //行业资讯爬虫
    @RequestMapping("getrealinfo")
    public List<RealtimeInfo> getInfo(){
       HtmlParseUtil htmlParseUtil=new HtmlParseUtil();
       return htmlParseUtil.parseInfo();
    }

}
