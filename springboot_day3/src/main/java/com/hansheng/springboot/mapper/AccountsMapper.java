package com.hansheng.springboot.mapper;

import com.hansheng.springboot.entity.Accounts;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountsMapper继承基类
 */
@Repository
public interface AccountsMapper extends MyBatisBaseDao<Accounts, Integer, AccountsExample> {
  //  List<Accounts> getAccountRoles();
    Accounts getAccountRoles(String loginName, String password);
}