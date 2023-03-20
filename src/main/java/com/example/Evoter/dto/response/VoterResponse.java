package com.example.Evoter.dto.response;

import com.example.Evoter.voter.data.model.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoterResponse {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String userEmailAddress;
}
