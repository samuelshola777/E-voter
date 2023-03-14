package com.example.Evoter.voter.service;

import com.example.Evoter.Address;
import com.example.Evoter.Gender;
import com.example.Evoter.voter.dto.request.VoterRequest;

import com.example.Evoter.voter.exception.VoterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
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
Address address6;
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
voter4.setPhoneNumber("09099332736");
voter4.setUserEmailAddress("fanusamuel@gmail.com");

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
voter6.setGender(Gender.FEMALE);


    }
    @Test
    void testThatVoterCanRegisterForVotersCard() throws VoterException, IOException {
      MockMultipartFile file =
      new MockMultipartFile("test_image",
              new FileInputStream("C:\\Users\\USER\\Downloads\\ST MEDIA-32.jpg"));
       var response = voterService.createVoteAccount(voter1,file);
      assertThat(response).isNotNull();
    }


}