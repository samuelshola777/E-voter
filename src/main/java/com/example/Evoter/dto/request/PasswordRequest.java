package com.example.Evoter.dto.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private String password;
    private String phoneNumber;
    private String voteNumber;
    private String userEmailAddress;
    private String newPassword;
}
