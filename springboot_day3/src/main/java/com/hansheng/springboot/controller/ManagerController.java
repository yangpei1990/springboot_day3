package com.hansheng.springboot.controller;


import com.github.pagehelper.PageInfo;
import com.hansheng.springboot.entity.Accounts;
import com.hansheng.springboot.entity.TblPermission;
import com.hansheng.springboot.entity.TblRoles;
import com.hansheng.springboot.service.AccountService;
import com.hansheng.springboot.service.PermissionService;
import com.hansheng.springboot.service.RoleService;
import com.hansheng.springboot.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    public AccountService accountService;

    @Autowired
    public RoleService roleService;

    @Autowired
    public PermissionService permissionService;

@RequestMapping("/accountList")
public String accountList(@RequestParam(defaultValue  = "1")int pageNum, @RequestParam(defaultValue = "5")int pageSize, HttpServletRequest request){
    PageInfo<Accounts> page = accountService.findByPage(pageNum, pageSize);
    request.setAttribute("page", page);
    return "manager/accountList";
}

    @RequestMapping("/roleList")
    public String roleList(@RequestParam(defaultValue  = "1")int pageNum, @RequestParam(defaultValue = "5")int pageSize, HttpServletRequest request){
        PageInfo<TblRoles> page = roleService.findByPage(pageNum, pageSize);
        request.setAttribute("page", page);
        return "manager/roleList";
    }

   /* 多表关联查询，自定义查询语句
    @RequestMapping("/permissionList")
    public String permissionList(@RequestParam(defaultValue  = "1")int pageNum, @RequestParam(defaultValue = "5")int pageSize, HttpServletRequest request){
        PageInfo<Accounts> page = accountService.getAccountRoles(pageNum, pageSize);
        request.setAttribute("page", page);
        return "/manager/permissionList";
    }*/
   @RequestMapping("/permissionList")
   public String permissionList(@RequestParam(defaultValue  = "1")int pageNum, @RequestParam(defaultValue = "5")int pageSize, HttpServletRequest request){
       PageInfo<TblPermission> page = permissionService.findByPage(pageNum, pageSize);
       request.setAttribute("page", page);
       return "manager/permissionList";
   }

    @RequestMapping("/modifyPage")
    public String modifyPage(@RequestParam int id, HttpServletRequest request){
        System.out.println(id);
       TblPermission permission = permissionService.findById(id);
        request.setAttribute("p", permission);
        return  "manager/permissionModify";
    }


    @RequestMapping("/modify/{id}")
    public String modify(@PathVariable int id, Model model){
        System.out.println(id);
        TblRoles role =  roleService.findRoleById(id);
        List listR = role.getPermissions();
        for (Object pm:listR) {
            System.out.println("TblPermission===================" + (TblPermission)pm);
        }
        model.addAttribute("listR", listR);
        model.addAttribute("role", role);
        List<TblPermission> list = permissionService.findAll();
        model.addAttribute("list", list);
        return "manager/roleModify";
       // return   ResultInfo.build(200,"成功。");
    }

}
