package com.swarmcn.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swarmcn.api.entity.User;
import com.swarmcn.api.service.IUserService;
import com.swarmcn.web.common.ResultInfo;
import com.swarmcn.web.util.json.JSONUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	IUserService userService;
	
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("logOn")
	public String logOn(String username,String password,HttpServletResponse response,HttpServletRequest request){
		try {
			HttpSession session = request.getSession();
			String result = userService.login(username, password);
			JSONObject object = JSONObject.fromObject(result);
			if(!object.getString("code").equals("success")){
				System.out.println(object.getString("message"));
				response.sendRedirect("/swarmcn-web/user/login");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
}
