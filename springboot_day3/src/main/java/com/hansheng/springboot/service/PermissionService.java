package com.hansheng.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hansheng.springboot.entity.TblPermission;
import com.hansheng.springboot.entity.TblRoles;
import com.hansheng.springboot.mapper.PermissionMapper;
import com.hansheng.springboot.mapper.TblPermissionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    public PageInfo<TblPermission> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TblPermission> list = permissionMapper.selectByExample(new TblPermissionExample());
        PageInfo<TblPermission> page = new PageInfo(list, 5);
        return page;
    }

    public TblPermission findById(int id) {
        TblPermission tblPermission =  permissionMapper.selectByPrimaryKey(id);
        return tblPermission;
    }

    public void updatePerms(TblPermission tblPermission){
    permissionMapper.updateByPrimaryKey(tblPermission);

    }

    public List<TblPermission> findAll() {
       return  permissionMapper.selectByExample(new TblPermissionExample());
    }
}