package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailManager {

	@Autowired
	JavaMailSender JMS;
	
	public String sendEmail(String toEmail, String subject, String message)
	{
		try {
		
		SimpleMailMessage mailmessage = new SimpleMailMessage();
		mailmessage.setFrom("harshitha.kelam@gmail.com");
		mailmessage.setTo(toEmail);
		mailmessage.setSubject(subject);
		mailmessage.setText(message);
		
		
		JMS.send(mailmessage);
		return "200::Password sent to your registered mail";
		
		}catch(Exception e)
		{
			return "401::"+ e.getMessage();
		}
				
	}
		
}