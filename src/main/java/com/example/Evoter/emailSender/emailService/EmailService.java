package com.example.Evoter.emailSender.emailService;

import com.example.Evoter.voter.data.model.Voter;

public interface EmailService {
    Voter sendEmail(Voter voter, String subject, String message);
}
