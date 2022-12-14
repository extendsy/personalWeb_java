package com.xiaoyi.personalweb.service.impl;

import com.xiaoyi.personalweb.entity.Result;
import com.xiaoyi.personalweb.entity.Role;
import com.xiaoyi.personalweb.mapper.RoleMapper;
import com.xiaoyi.personalweb.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Result addRole(Role role) {
        int row = roleMapper.insert(role);
        return Result.getSuccessResult(row);
    }

    @Override
    public Result queryList(Role role) {
        List<Role> roles = roleMapper.selectList(null);
        return Result.getSuccessResult(roles);
    }


}
