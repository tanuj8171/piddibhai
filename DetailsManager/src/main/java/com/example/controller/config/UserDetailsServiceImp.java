package com.example.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.dao.UserRepository;
import com.example.demo.entities.User;

public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	//	fetching user from database;
	User user=userRepository.getUserByUserName(username);
		
		if(user==null) 
		{
	     throw new UsernameNotFoundException("Could Not found user !!");}
         CustomUserDetails customUserDetails=new CustomUserDetails(user);
	     return customUserDetails;
	}

}
