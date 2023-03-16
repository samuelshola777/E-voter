package com.example.Evoter.dto.request;

import com.example.Evoter.address.data.model.Address;
import com.example.Evoter.voter.data.model.Gender;
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
