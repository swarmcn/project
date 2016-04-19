package com.swarmcn.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swarmcn.api.entity.User;
import com.swarmcn.api.service.IUserService;
import com.swarmcn.user.dao.UserMapper;
import com.swarmcn.user.util.ResultInfo;
import com.swarmcn.user.util.json.JSONUtil;

@Service
public class UserService implements IUserService{

	@Autowired
	UserMapper userMapper;
	
	public String login(String username, String password) {
		ResultInfo info = new ResultInfo();
		User user = userMapper.findUserByUsername(username);
		if(user!=null){
			if(user.getPassword().equals(password)){
				info.setCode("success");
				info.setData(user);
			}else{
				info.setCode("fail");
				info.setMessage("密码不正确");
			}
		}else{
			info.setCode("fail");
			info.setMessage("用户名不存在");
		}
		return JSONUtil.getJSONFromObject(info);
	}

	public String regist(String username, String password) {
		return "success";
	}

	public User find(Integer id) {
		User user = new User();
		user.setUsername("abc");
		user.setPassword("123");
		return user;
	}

}
