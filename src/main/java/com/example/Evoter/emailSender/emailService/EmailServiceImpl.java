package com.example.Evoter.emailSender.emailService;

import com.example.Evoter.voter.data.model.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public Voter sendEmail(Voter voter, String subject, String message) {

    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("samuelShola14@gmail.com");
    mailMessage.setTo(voter.getUserEmailAddress());
    mailMessage.setSubject(subject);
    mailMessage.setText(message);
    mailSender.send(mailMessage);
    return voter;
    }
}
