package com.hansheng.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hansheng.springboot.entity.Accounts;
import com.hansheng.springboot.mapper.AccountsExample;
import com.hansheng.springboot.mapper.AccountsMapper;
import com.hansheng.springboot.mapper.MenuMapper;
import com.hansheng.springboot.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    public AccountsMapper accountsMapper;
    @Autowired
    public MenuMapper menuMapper;
    public Accounts findByLoginNameAndPassword(String loginName, String password){
        AccountsExample example = new AccountsExample();
        example.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
        List<Accounts> list =  accountsMapper.selectByExample(example);
        return list.size() ==0 ? null : list.get(0);
    }

    public List<Accounts> findAll() {
        List<Accounts> list =  accountsMapper.selectByExample(new AccountsExample());
        return list;
    }

  /*  public PageInfo<Accounts> getAccountRoles(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Accounts> list = accountsMapper.getAccountRoles();
        PageInfo<Accounts> pageInfo = new PageInfo(list, 5);
        return pageInfo;
    }*/
    public Accounts getAccountRolePermission(String loginName, String password){
        return  accountsMapper.getAccountRoles(loginName, password);
    }

    public PageInfo<Accounts> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Accounts> list =  accountsMapper.selectByExample(new AccountsExample());
        PageInfo<Accounts> pageinfo = new PageInfo(list, 5);
        return pageinfo;
    }

    public ResultInfo deleteById(int id) {
        int row =  accountsMapper.deleteByPrimaryKey(id);
        if(row>0)
            return ResultInfo.build(200);
        else
            return ResultInfo.build(500, "s删除出错");
    }

    public void updateAccountInfo(Accounts account) {
        accountsMapper.updateByPrimaryKeySelective(account);
    }
}
