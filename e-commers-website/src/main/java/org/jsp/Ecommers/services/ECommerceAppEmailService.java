package org.jsp.Ecommers.services;

import org.jsp.Ecommers.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import static org.jsp.Ecommers.util.ApplicationConstants.VERIFY_LINK;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ECommerceAppEmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public String sendWelcomeMail(Merchant merchant, HttpServletRequest request) {
		String siteUrl = request.getRequestURL().toString();
		String url = siteUrl.replace(request.getServletPath(), "");
		String actual_url = url + VERIFY_LINK + merchant.getToken();
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(merchant.getEmail());
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
