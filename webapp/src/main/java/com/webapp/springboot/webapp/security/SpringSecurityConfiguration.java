package com.webapp.springboot.webapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	//InMemoryUserDetailsManager
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager()
	{
		UserDetails userDetails1=createNewUser("Rahul", "password");
		UserDetails userDetails2=createNewUser("shridhar", "password");
		return new InMemoryUserDetailsManager(userDetails1,userDetails2);
	}
	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder
		= input -> passwordEncoder().encode(input);

		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("USER","ADMIN")
									.build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//All URLs are protected
	//A login form is shown for unauthorized requests
	//CSRF disable
	//Frames
	
	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
	 
	    httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()); // enables authentication to all requests/apis
	    httpSecurity.formLogin(Customizer.withDefaults()); // enables login form to be displayed for un-authorized requests
	    httpSecurity.csrf(csrf -> csrf.disable()); // disables cross-site request forgery (enable access to h2-console)
	    httpSecurity.headers(header->header.frameOptions(frameOptions->frameOptions.disable())); // disables protection against iFrame usage, needed by h2-console
	    return httpSecurity.build();
	}

	
}
	
	

