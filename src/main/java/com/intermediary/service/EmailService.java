package com.intermediary.service;

import com.intermediary.dto.EmailDTO;

public interface EmailService {
	public boolean sendEmail(EmailDTO emailDTO);
	
	public EmailDTO construirEmail(String email, String asunto, String contenido);
}
