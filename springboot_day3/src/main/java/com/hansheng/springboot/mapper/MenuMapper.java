package com.hansheng.springboot.mapper;

import com.hansheng.springboot.entity.Menu;
import org.springframework.stereotype.Repository;

/**
 * MenuMapper继承基类
 */
@Repository
public interface MenuMapper extends MyBatisBaseDao<Menu, Integer, MenuExample> {
}