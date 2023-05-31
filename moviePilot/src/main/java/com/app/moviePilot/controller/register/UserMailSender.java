package com.app.moviePilot.controller.register;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.app.moviePilot.model.user.ActiveUser;
/**
 * 
 * @author Marino Burillo
 *
 */
@Component
public class UserMailSender {
	private static final String FILE_NAME = "application.properties";
	private static final String FILE_PATH = "src/main/resources";
	@Autowired
	private JavaMailSender javaMailSender;

	public boolean sendEmail(String origin, ActiveUser u) {
        Properties properties = new Properties();
        Path filePath = Path.of(FILE_PATH, FILE_NAME);
        try {
            InputStream inputStream = Files.newInputStream(filePath);
            properties.load(inputStream);
            String subject = "Confirm your email";
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(properties.getProperty("spring.mail.username"));
            messageHelper.setTo(u.getEmail());
            messageHelper.setSubject(subject);
            String htmlContent = "Dear [[name]],<br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "[[URL]] <br>"
                    + "Thank you,<br>"
                    + "MoviePilot.";
            htmlContent = htmlContent.replace("[[name]]", u.getUserName());
            htmlContent = htmlContent.replace("[[URL]]", "<a href='http://"+origin+"?"+u.getVerificationCode()+"'> Verify </a>");
            messageHelper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (IOException | MessagingException e) {
            return false;
        }

        return true;
    }

}
