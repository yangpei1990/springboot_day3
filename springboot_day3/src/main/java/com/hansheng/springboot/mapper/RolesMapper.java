package com.hansheng.springboot.mapper;

import com.hansheng.springboot.entity.TblRoles;
import org.springframework.stereotype.Repository;

/**
 * RolesMapper继承基类
 */
@Repository
public interface RolesMapper extends MyBatisBaseDao<TblRoles, Integer, TblRolesExample> {
    void addPermission(int id, int permission);
    void addPermissions(int id, int[] permissions);

    TblRoles selectById(int id);
}