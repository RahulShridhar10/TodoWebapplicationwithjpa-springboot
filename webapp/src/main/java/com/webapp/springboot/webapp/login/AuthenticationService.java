package com.webapp.springboot.webapp.login;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {
public boolean authenticate(String username,String password)
	{
	
		boolean isValidUserName=username.equalsIgnoreCase("Rahul");
		boolean isValidPassword=password.equals("password");
		System.out.println(isValidUserName && isValidPassword);
		return isValidUserName && isValidPassword;
	}
}
