package com.example.Evoter.dto.request;

import com.example.Evoter.address.data.model.Address;
import lombok.Data;

@Data
public class PartyRequest {
    private long id;
    private String partyName;
    private String partyChairMan;
    private Address headOfficeAddress;
    private String candidateName;
    private String partyEmailAddress;
    private String partyPhoneNumber;
}
