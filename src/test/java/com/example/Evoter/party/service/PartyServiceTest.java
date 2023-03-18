package com.example.Evoter.party.service;

import com.example.Evoter.address.data.model.Address;
import com.example.Evoter.dto.request.PartyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PartyServiceTest {
    PartyRequest partyRequest1;
    PartyRequest partyRequest2;
    Address address1;
    Address address2;


    @BeforeEach
    void setUp() {
address1.setHouseNumber("67");
address1.setState("lagos");
address1.setStreetName("obga");
address1.setLocalGovernment("ogba");
        partyRequest1 = new PartyRequest();
        partyRequest1.setPartyName("APC");
        partyRequest1.setPartyChairMan("TINUBU");
        partyRequest1.setPartyEmailAddress("apc@gmail.com");
        partyRequest1.setPartyPhoneNumber("0904433223442");
        partyRequest1.setCandidateName("TINUBU");
        partyRequest1.setHeadOfficeAddress(address1);
address2.setHouseNumber("67");
address2.setState("lagos");
address2.setStreetName("alausa");
address2.setLocalGovernment("ikeja");
        partyRequest2 = new PartyRequest();
        partyRequest2.setPartyName("DPD");
        partyRequest2.setPartyChairMan("ATIKU");
        partyRequest2.setPartyEmailAddress("DPD@gmail.com");
        partyRequest2.setPartyPhoneNumber("080499903442");
        partyRequest2.setCandidateName("ATIKU");
        partyRequest2.setHeadOfficeAddress(address2);
    }
}