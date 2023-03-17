package com.example.Evoter.party.data.model;

import com.example.Evoter.address.data.model.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Party {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String partyName;
    private String partyChairMan;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address headOfficeAddress;
    private String candidateName;
    private String partyEmailAddress;
    private String partyPhoneNumber;

}
