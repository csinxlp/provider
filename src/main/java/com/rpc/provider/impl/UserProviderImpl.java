package com.rpc.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpc.model.User;
import com.rpc.provider.UserProvider;
import com.rpc.service.UserService;

@Service("userProvider")
public class UserProviderImpl implements UserProvider{
	
	@Autowired
	private UserService userService;
	
	@Override
	public int deleteByPrimaryKey(Long userId) {
		return userService.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(User record) {
		return userService.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return userService.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Long userId) {
		return userService.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userService.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return userService.updateByPrimaryKey(record);
	}

}
