package com.example.Evoter.voter.dto.request;

import com.example.Evoter.Address;
import com.example.Evoter.Gender;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Data
public class VoterRequest {
    private String firstName;
    private String lastName;
    private  String password;
    private String phoneNumber;
    private String voteNumber;
    private String userEmailAddress;
    private Address voterAddress;
    private String occupation;
    private String voterCandidate;
    private Gender gender;

}
