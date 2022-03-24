package com.example.controller;

import javax.persistence.Column;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.controller.helper.Message;
import com.example.dao.UserRepository;
import com.example.demo.entities.Details;
import com.example.demo.entities.User;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	/*@GetMapping("/test")
	@ResponseBody
	public String test()
	{
		User user=new User();
		user.setName("Aman verma");
		user.setEmail("iamanverma19@gmail.com");
		
		Details det =new Details();
		user.getDetails().add(det);	
		
		userRepository.save(user);
		return "working";
	}*/

	
	
	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title","Home - Details Manager");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title","About - Details Manager");
		return "about";
	}
	@RequestMapping("/signUp")
	public String signUp(Model model)
	{
		model.addAttribute("title","signUp - Details Manager");
		model.addAttribute("user",new User());
		return "signUp";
	}
    //handler  for registering user
	@RequestMapping(value="/do register",method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result1,@RequestParam(value="agreement",defaultValue="false")boolean agreement,Model model,HttpSession session)
	{
		try {
			if(!agreement)
			{
				System.out.println("You have not agreed the terms and conditions");
				throw new Exception("You have not agreed the terms and conditions");
			}
			
			if(result1.hasErrors())
			{
				System.out.println("ERROR " +result1.toString());
				model.addAttribute("user",user);
			    return "signUp";	
			}
			
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			System.out.println("Agreement "+agreement);
			System.out.println("USER "+user);
			 
			User result=this.userRepository.save(user);
			
	        model.addAttribute("user",new User());
	        
	        session.setAttribute("message", new Message("Successfully Registered !!","alert-success"));
			return "signUp";
		}
		 catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something Went wrong !!"+e.getMessage(),"alert-danger"));
			return "signUp";
		}
	}
}
