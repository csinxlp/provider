package com.rpc.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.rpc.model.User;

@MapperScan
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}