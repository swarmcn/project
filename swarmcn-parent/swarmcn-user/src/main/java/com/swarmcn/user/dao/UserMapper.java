package com.swarmcn.user.dao;

import com.swarmcn.api.entity.User;

public interface UserMapper {

	User findUserByUsername(String username);
	
}
