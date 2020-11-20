package com.hansheng.springboot.controller.rest;

import com.hansheng.springboot.entity.TblPermission;
import com.hansheng.springboot.service.PermissionService;
import com.hansheng.springboot.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/manager/permission")
public class PermissionManagerRestController {
    @Autowired
    public PermissionService permissionService;
    @RequestMapping("/modify")
    public Object modify(@RequestBody TblPermission tblPermission){
        permissionService.updatePerms(tblPermission);
        return   ResultInfo.build(200,"成功。");
    }
}
