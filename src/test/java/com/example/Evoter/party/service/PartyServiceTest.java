package com.example.Evoter.party.service;

import com.example.Evoter.address.data.model.Address;
import com.example.Evoter.dto.request.PartyRequest;
import com.example.Evoter.party.data.model.Party;
import com.example.Evoter.voter.exception.PartyException;
import com.example.Evoter.voter.exception.PartyRegistrationException;
import com.example.Evoter.voter.exception.VoterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PartyServiceTest {
    @Autowired
    PartyService partyService;
    PartyRequest partyRequest1;
    PartyRequest partyRequest2;
    PartyRequest partyRequest3;
    Address address1;
    Address address2;
    Address address3;


    @BeforeEach
    void setUp() {
address1 = new Address();
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
address2 = new Address();
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

address3 = new Address();
address3.setHouseNumber("5");
address3.setState("osun");
address3.setStreetName("OYINGB");
address3.setLocalGovernment("OYINGB0");
        partyRequest3 = new PartyRequest();
        partyRequest3.setPartyName("LABOR-PARTY");
        partyRequest3.setPartyChairMan("PITER-OB");
        partyRequest3.setPartyEmailAddress("laborParty@gmail.com");
        partyRequest3.setPartyPhoneNumber("080499903442");
        partyRequest3.setCandidateName("PITER-OBI");
        partyRequest3.setHeadOfficeAddress(address3);
    }
@Disabled
    @Test
    void testThatWeCanRegisterParty() throws PartyRegistrationException, VoterException {

assertEquals("party registration completed successfully", partyService.registerParty(partyRequest1));
assertEquals("party registration completed successfully", partyService.registerParty(partyRequest2));
assertEquals("party registration completed successfully", partyService.registerParty(partyRequest3));
    }
    @Test
    void testThatWeCanFindPartyById() throws PartyException {
     assertEquals("DPD", partyService.findPartyById(2).getPartyName());
    }
    @Test
    void testThatWCanDeletePartyById() throws PartyException {
        partyService.deletePartyById(2);
        assertEquals(2,partyService.countRegisteredParty());

    }
}