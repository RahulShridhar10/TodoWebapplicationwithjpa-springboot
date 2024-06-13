package com.webapp.springboot.webapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
//"say-Hello"
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello()
	{
		return "HEllo bro";
	}
	@RequestMapping("hello-jsp")
	//store jsp in /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	//@ResponseBody
	public String sayHellojsp()
	{
		return "sayHello";
	}
}

