package com.hansheng.springboot.mapper;

import com.hansheng.springboot.entity.TblPermission;
import org.springframework.stereotype.Repository;

/**
 * PermissionMapper继承基类
 */
@Repository
public interface PermissionMapper extends MyBatisBaseDao<TblPermission, Integer, TblPermissionExample> {
}