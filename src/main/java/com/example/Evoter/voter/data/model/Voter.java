package com.example.Evoter.voter.data.model;

import com.example.Evoter.address.data.model.Address;
import com.example.Evoter.voter.exception.VoterException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "name cannot be empty")
    private String firstName;
    private String lastName;
    private LocalDate registrationDate = LocalDate.now();
    private LocalTime registrationTime = LocalTime.now();
    private String password;
    private String phoneNumber;
    private String voteNumber;
    private String userEmailAddress;
    private LocalDateTime starAt;
    private LocalDateTime endAt;
    @JsonIgnore
    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address voterAddress;
    private String occupation;
    private String voterCandidate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int registrationYear = LocalDateTime.now().getYear();

    public void setPhoneNumber(String phoneNumber) throws VoterException {
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i)))
                throw new VoterException("you have entered an invalid phone number");
        }
        if (phoneNumber.length() != 11) throw new VoterException("you have entered an invalid length of phone number");
        this.phoneNumber = phoneNumber;
    }
}


