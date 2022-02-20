package com.intermediary.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.dto.EmailDTO;
import com.intermediary.exception.GenericExecption;
import com.intermediary.service.EmailService;

public class EmailServiceImpl implements EmailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired(required = true)
	private JavaMailSender sender;

	@Override
	public boolean sendEmail(EmailDTO emailDTO) {
		return sendEmailool(emailDTO.getContent(), emailDTO.getEmail(), emailDTO.getSubject());
	}

	private boolean sendEmailool(String content, String email, String subject) {
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(email);
			helper.setText(content, true);
			helper.setSubject(subject);
			sender.send(message);
			send = true;
			LOGGER.info("Mail enviado!");
		}catch (MessagingException e) {
			LOGGER.error("Hubo un error al enviar el mail: {}", e);
			throw new GenericExecption(CatalogoMensajesGenerales.ERROR_ENVIAR_MENSAJE, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return send;
	}

}
