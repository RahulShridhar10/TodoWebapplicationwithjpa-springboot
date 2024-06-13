package com.webapp.springboot.webapp.login;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class welcomeController 
{
	@Autowired
	private AuthenticationService authenticationservice;
//	private Logger logger=LoggerFactory.getLogger(getClass());
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String loginPage(ModelMap model)
	{
		model.put("name", getLoggedinUsername());
//		System.out.println("Request Param is "+name);
//		logger.debug("Request param is {} at debug level",name);
//		logger.info("Request param is {} at info level",name);
//		model.put("name",name);
		return "welcome";
	}
		//because of spring security we avoid authentication service
//	@RequestMapping(value="login",method = RequestMethod.POST)
//	public String welcomePage(@RequestParam String name,@RequestParam String password,ModelMap model)
//	{
//		if(authenticationservice.authenticate(name, password))
//		{
//			model.put("name", name);
//			model.put("password", password);
//			return "welcome";	
//		}
//		model.put("errormessage", "Invalid credentials please try again!");
//		return "Login";
//	}
	private String getLoggedinUsername()
	{
		org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
}
