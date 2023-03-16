package com.example.Evoter.voter.service;

import com.example.Evoter.address.data.model.Address;
import com.example.Evoter.voter.data.model.Gender;
import com.example.Evoter.voter.data.model.Voter;
import com.example.Evoter.voter.dto.request.VoterRequest;

import com.example.Evoter.voter.exception.VoterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
class VoterServiceImplTest {
VoterRequest voter1;
Address address1;
VoterRequest voter2;
Address address2;
VoterRequest voter3;
Address address3;
VoterRequest voter4;
Address address4;
VoterRequest voter5;
Address address5;
VoterRequest voter6;
VoterRequest voter7;
VoterRequest voter8;
Address address6;
Address address7;
Address address8;
@Autowired
VoterService voterService;
    @BeforeEach
    void setUp() {
    address1 = new Address();
    address1.setHouseNumber("103");
    address1.setStreetName("ilaje-road");
    address1.setLocalGovernment("ilaje");
    address1.setState("lagos");

    
    voter1 = new VoterRequest();
    voter1.setFirstName("samuel");
    voter1.setLastName("shola");
    voter1.setPassword("blueBoat");
    voter1.setPhoneNumber("09099332737");
    voter1.setUserEmailAddress("samuelshola14@gmail.com");
    voter1.setVoterAddress(address1);
    voter1.setOccupation("software-engineer");
    voter1.setGender(Gender.MALE);

        address2 = new Address();
        address2.setHouseNumber("20");
        address2.setStreetName("kajola");
        address2.setLocalGovernment("shomolu");
        address2.setState("lagos");

voter2 = new VoterRequest();
voter2.setFirstName("marthin");
voter2.setLastName("black");
voter2.setPassword("blackIsWhite");
voter2.setPhoneNumber("08124374582");
voter2.setUserEmailAddress("jojololamartins686@gmail.com");
voter2.setVoterAddress(address2);
voter2.setOccupation("software-engineer");
voter2.setGender(Gender.MALE);

        address3 = new Address();
        address3.setHouseNumber("209");
        address3.setStreetName("nnewi");
        address3.setLocalGovernment("sambisa");
        address3.setState("kogi");

voter3 = new VoterRequest();
voter3.setFirstName("glory");
voter3.setLastName("kachi");
voter3.setPassword("saheed");
voter3.setPhoneNumber("04025384329");
voter3.setUserEmailAddress("kachikachina@gmail.com");
voter3.setVoterAddress(address3);
voter3.setOccupation("back-end engineer");
voter3.setGender(Gender.FEMALE);

        address4 = new Address();
        address4.setHouseNumber("2");
        address4.setStreetName("ile-iwe");
        address4.setLocalGovernment("oshobgo");
        address4.setState("osun");

voter4 = new VoterRequest();
voter4.setFirstName("samuel");
voter4.setLastName("segun");
voter4.setPassword("samosa");
voter4.setVoterAddress(address4);
voter4.setPhoneNumber("09099332736");
voter4.setUserEmailAddress("fanusamuel@gmail.com");
voter4.setGender(Gender.MALE);

        address5 = new Address();
        address5.setHouseNumber("2");
        address5.setStreetName("sabo");
        address5.setLocalGovernment("kelechi");
        address5.setState("kwara");
voter5 = new VoterRequest();
voter5.setFirstName("richard");
voter5.setLastName("richyMoney");
voter5.setPassword("SetOfBabe");
voter5.setPhoneNumber("03045542344");
voter5.setUserEmailAddress("essienriicch98@outlook.com");
voter5.setOccupation("java-engineer");
voter5.setGender(Gender.MALE);
voter5.setVoterAddress(address5);
        address6 = new Address();
        address6.setHouseNumber("25");
        address6.setStreetName("fola-goro");
        address6.setLocalGovernment("abul-ijesha");
        address6.setState("lagos");
voter6 = new VoterRequest();
voter6.setFirstName("nnema");
voter6.setLastName("jonathan");
voter6.setPassword("samuel-shola");
voter6.setPhoneNumber("08142465973");
voter6.setUserEmailAddress("nenman.williams@gmail.com");
voter6.setOccupation("full stack engineer");
voter6.setVoterAddress(address6);
voter6.setGender(Gender.FEMALE);
 address7 = new Address();
        address7.setHouseNumber("25");
        address7.setStreetName("beger");
        address7.setLocalGovernment("abul-ijesha");
        address7.setState("lagos");
voter7 = new VoterRequest();
voter7.setFirstName("ore");
voter7.setLastName("beauti");
voter7.setPassword("samuel-shola");
voter7.setPhoneNumber("081424659556");
voter7.setUserEmailAddress("orework22@gmail.com");
voter7.setOccupation("full stack engineer");
voter7.setVoterAddress(address7);
voter7.setGender(Gender.FEMALE);
        address8 = new Address();
        address8.setHouseNumber("25");
        address8.setStreetName("beger");
        address8.setLocalGovernment("abul-ijesha");
        address8.setState("lagos");
voter8 = new VoterRequest();
voter8.setFirstName("Haneefah");
voter8.setLastName("beauti");
voter8.setPassword("samuel-shola");
voter8.setPhoneNumber("0814 456 5016");
voter8.setUserEmailAddress("motunrayor2@gmail.com");
voter8.setOccupation("full stack engineer");
voter8.setVoterAddress(address8);
voter8.setGender(Gender.FEMALE);


    }
    @Disabled
    @Test
    void testThatVoterCanRegisterForVotersCard() throws VoterException, IOException {


    voterService.createVoteAccount(voter1);
    voterService.createVoteAccount(voter2);
    voterService.createVoteAccount(voter3);
    voterService.createVoteAccount(voter4);
    voterService.createVoteAccount(voter5);
    voterService.createVoteAccount(voter6);
    voterService.createVoteAccount(voter7);
    voterService.createVoteAccount(voter8);
    }

@Test
    void testThatWeCanFindById() throws VoterException {
        assertEquals("samuel",voterService.findByVoterById(1).getFirstName());
}
@Test
void testThatWeCanFindVotersByEmailAddress() throws VoterException {
        assertEquals("shola",voterService.findByVoterByEmail("samuelshola14@gmail.com").getLastName());
}
@Disabled
@Test
    void testThatVoterCanChangePassword() throws VoterException {
       assertEquals("BONESHAKER", voterService.changePassword("samuelshola14@gmail.com","nt57791sa","BONESHAKER"));
        assertEquals("GARRY-MARTINS", voterService.changePassword("jojololamartins686@gmail.com","nt75384ma","GARRY-MARTINS"));
        assertEquals("GARRY-MARTINS", voterService.changePassword("nenman.williams@gmail.com","nt61496nn","GARRY-MARTINS"));
        assertEquals("i_look_cute", voterService.changePassword("orework22@gmail.com","nt57588or","i_look_cute"));
        assertEquals("i_look_beautiful", voterService.changePassword("motunrayor2@gmail.com","nt53840Ha","i_look_beautiful"));
}



}