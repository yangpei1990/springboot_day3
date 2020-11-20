package com.hansheng.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hansheng.springboot.entity.TblRoles;
import com.hansheng.springboot.mapper.RolesMapper;
import com.hansheng.springboot.mapper.TblRolesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    public RolesMapper rolesMapper;

    public PageInfo<TblRoles> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TblRoles> list =  rolesMapper.selectByExample(new TblRolesExample());
        PageInfo<TblRoles> pageinfo = new PageInfo(list, 5);
        return pageinfo;
    }

 /*   public TblRoles findRoleById(int id) {
        return rolesMapper.selectByPrimaryKey(id);
    }*/
    public TblRoles findRoleById(int id) {
        return rolesMapper.selectById(id);
    }

    public void addPermissions(int id, int[] permissions) {
       // for (int p: permissions) {
           // rolesMapper.addPermission(id, p);
        rolesMapper.addPermissions(id, permissions);
       // }

    }
}
