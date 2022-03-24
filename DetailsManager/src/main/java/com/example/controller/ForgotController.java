package com.example.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.EmailService;

@Controller
public class ForgotController {
	Random random=new Random(1000);
	
	@Autowired
	private EmailService emailService;

	@RequestMapping("/fogot")
     public String openEmailForm()
     {
    	 return "forgot_email_form";
     }
	@PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email")String email,HttpSession session)
    {
		System.out.println("EMAIL "+email);
		
		//generating OTP of 4 digit
		
		
		int otp=random.nextInt(999999);
		System.out.println("OTP "+otp);
		
		//write code for send otp to email....
		
		String subject="OTP from DM";
		String message="<h1>OTP ="+otp+" </h1>";
		String to=email;
		
		boolean flag=this.emailService.sendEmail(subject,message,to);

		if(flag)
		{
			 return "verify_OTP";
		}else {
			
			session.setAttribute("message", "Check your email id!! ");
			return "forgot_email_form";
		}
   	
    }

}
