package com.swarmcn.api.service;

import com.swarmcn.api.entity.User;

public interface IUserService {
	public String login(String username,String password);
	public String regist(String username,String password);
	public User find(Integer id);
}
