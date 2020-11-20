package com.hansheng.springboot.controller;


import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.hansheng.springboot.entity.Accounts;
import com.hansheng.springboot.entity.TblPermission;
import com.hansheng.springboot.entity.TblRoles;
import com.hansheng.springboot.service.AccountService;
import com.hansheng.springboot.utils.ResultInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    public FastFileStorageClient fs;
    @Autowired
    public AccountService accountService;
    @RequestMapping("/index")
    public String index(){
        System.out.println("index");
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        System.out.printf("login");
        return "account/login";
    }
    @RequestMapping("/logOut")
    public String loginOut(HttpServletRequest request){
        //退出登录，删除session
        request.getSession().removeAttribute("account");
        return "/index";
    }
    @RequestMapping("/validataAccount")
    @ResponseBody
    public String validataAccount(String loginName, String password, HttpServletRequest request){
        System.out.println(loginName);
        System.out.println(password);

        Accounts account = accountService.getAccountRolePermission(loginName, password);
        System.out.println("getPermissions" + account.getPermissions().size());
        for (TblPermission pm: account.getPermissions()) {
            System.out.println("pm=================================" + pm.toString());
        }
        for (TblRoles r: account.getRoles()) {
            System.out.println(r.toString());
        }
        //accountService.findByLoginNameAndPassword(loginName, password);
        if(null != account){
            //登录成功，将用户信息保存到session
            request.getSession().setAttribute("account", account);
            return "success";

        }else {
            System.out.println("用户名或密码不存在。");
            return null;
        }
    }

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue  = "1")int pageNum, @RequestParam(defaultValue = "5")int pageSize, HttpServletRequest request){
        PageInfo<Accounts> page = accountService.findByPage(pageNum, pageSize);
        request.setAttribute("page", page);
        return "account/list";
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public ResultInfo deleteById(@RequestParam(defaultValue  = "1")int id){
        return  accountService.deleteById(id);
    }


    @RequestMapping("/profile")
    public String profile(){
        System.out.println("profile .....");
      return "account/profile";
    }
    @RequestMapping("/fileUploadController")
    public String fileUploadController(MultipartFile filename, String password, HttpServletRequest request){
        System.out.println("password:" + password);
        System.out.println("file:" + filename.getOriginalFilename());
        Accounts account = (Accounts) request.getSession().getAttribute("account");
      //  try {
        try {
            //文件的原生信息
            Set<MetaData> metaDataSet = new HashSet<MetaData>();
            metaDataSet.add(new MetaData("Author", "yp"));
            metaDataSet.add(new MetaData("CreateDate", "2020-11-03"));
            StorePath uploadFile = null;
            //上传原文件到fastdfs文件服务器
            //uploadFile = fs.uploadFile(filename.getInputStream(), filename.getSize(), FilenameUtils.getExtension(filename.getOriginalFilename()), metaDataSet);
            //上传原文件到fastdfs文件服务器，并按照设置生成缩略图
            uploadFile = fs.uploadImageAndCrtThumbImage(filename.getInputStream(), filename.getSize(), FilenameUtils.getExtension(filename.getOriginalFilename()), metaDataSet);
            account.setPassword(password);
            account.setLocation(uploadFile.getPath());
          //  File path = new File(ResourceUtils.getURL("classpath:").getPath());
           // File upload = new File(path.getAbsolutePath(), "static/upload/");
            //System.out.println("upload:" + upload);
          //  filename.transferTo(new File("c:/upload/"+filename.getOriginalFilename().substring(filename.getOriginalFilename().lastIndexOf("\\"))));
            //account.setPassword(password);
            //account.setLocation(filename.getOriginalFilename().substring(filename.getOriginalFilename().lastIndexOf("\\")));
            accountService.updateAccountInfo(account);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
      //  } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
        //    e.printStackTrace();
      //  } catch (IOException e) {
            // TODO Auto-generated catch block
        //    e.printStackTrace();
     //   }

        return "account/profile";
}

    public static void main(String[] args) {
        System.out.printf(FilenameUtils.getExtension("t.txt"));

    }
}
