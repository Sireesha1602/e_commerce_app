package org.jsp.Ecommers.services;

import static org.jsp.Ecommers.util.ApplicationConstants.VERIFY_LINK;

import org.jsp.Ecommers.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
@Service
public class EmailServiceUser {
	@Autowired
	private JavaMailSender javaMailSender;

	public String sendWelcomeMail(User user, HttpServletRequest request) {
		String siteUrl = request.getRequestURL().toString();
		String url = siteUrl.replace(request.getServletPath(), "");
		String actual_url = url + VERIFY_LINK + user.getToken();
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(user.getEmail());
			helper.setSubject("Account Activation Mail");
			helper.setText(actual_url);
			javaMailSender.send(message);
			return "Verification Mail has been sent";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "cannot send the verification mail";
		}

	}


}
