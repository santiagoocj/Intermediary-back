package com.intermediary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
	
	@Secured("ROLE_ADMINISTRADOR")
	@PostMapping("/send")
	public ResponseEntity<Object> sendEmail(@RequestBody EmailDTO emailDTO) {
		if(emailServiceImpl.sendEmail(emailDTO)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
