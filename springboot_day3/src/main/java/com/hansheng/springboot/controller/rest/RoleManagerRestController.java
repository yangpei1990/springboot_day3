package com.hansheng.springboot.controller.rest;

import com.hansheng.springboot.entity.TblPermission;
import com.hansheng.springboot.service.PermissionService;
import com.hansheng.springboot.service.RoleService;
import com.hansheng.springboot.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager/role")
public class RoleManagerRestController {
    @Autowired
    public RoleService roleService;
    @RequestMapping("/add")
    public Object modify(@RequestParam int id, @RequestParam int[] permissions){
      //  ToStringBuilder.reflectionToString(permissions);
     //   System.out.println(id);
        roleService.addPermissions(id, permissions);
        System.out.println("permissions===========" +  permissions);
        return   ResultInfo.build(200,"成功。");
    }
}
