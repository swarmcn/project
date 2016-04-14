package com.swarmcn.user;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) throws IOException {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"META-INF/spring/provider.xml"});
	        context.start();
	        System.in.read(); 
	}

}
