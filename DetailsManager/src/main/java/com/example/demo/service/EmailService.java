package com.example.demo.service;

import java.util.Properties;

import javax.mail.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	public boolean sendEmail(String subject,String message,String to) {
		//rest of the code..
		boolean f=false;
		
		String from="techSoftinfindia@gamil.com";
		
		//variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties =System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		
		
		//step1: to get the session object..
		Session session =Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("technosoftindia@gmail.com","");
			}
		});
		session.setDebug(true);
		
		//step2: compose the message[text,multi media]
		MimeMessage m=new MimeMessage(session);
		
		try {
			//from email
			m.setFrom(from);
			
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
			m.setText(message);
			
			//send
			
			//step3: send the message using transport class	
			Transport.send(m);
			System.out.println("Sent sucess........");
			f=true;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	   return f;
	}

}
