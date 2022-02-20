package com.intermediary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.dto.EmailDTO;
import com.intermediary.service.impl.EmailServiceImpl;

@Controller
@RequestMapping("/api")
public class EmailController {
	
	@Autowired
	public EmailServiceImpl emailServiceImpl;
	
	@PostMapping("/send")
	public void sendEmail(@RequestBody EmailDTO emailDTO) {
		emailServiceImpl.sendEmail(emailDTO);
	}

}
