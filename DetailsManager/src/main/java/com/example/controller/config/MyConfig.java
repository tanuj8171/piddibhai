package com.example.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfiguration {

	/* @Bean
	 public UserDetailsService getUserDetailService() {
		 
		 return new UserDetailsServiceImp();
	 }
     
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider()
	 {
		 DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
	     daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
	  //   daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	     return daoAuthenticationProvider;*/
	// }
	 
	 //configure method....
	 
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		// super.setFilterChainProxySecurityConfigurer(auth);
//	 }
	 
//}
}