package com.example.Evoter.voter.service;

import com.example.Evoter.cloud.CloudInterface;
import com.example.Evoter.emailSender.emailService.EmailServiceImpl;
import com.example.Evoter.voter.data.model.Voter;
import com.example.Evoter.voter.data.repository.VoterRepository;
import com.example.Evoter.voter.dto.request.VoterRequest;
import com.example.Evoter.voter.dto.response.VoterResponse;
import com.example.Evoter.voter.exception.VoterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;

@Service
public class VoterServiceImpl implements VoterService{
@Autowired
CloudInterface cloudInterface;
@Autowired
EmailServiceImpl emailService;
@Autowired
VoterRepository veeRepo;

    @Override
public Voter createVoteAccount(VoterRequest voterRequest, MultipartFile image) throws VoterException {
Voter builtVoter = buildVoter(voterRequest);
Voter voterEmail = sendRegistationMail(builtVoter);
//var imageUrl = cloudInterface.upload(image);
Voter tokenedVoter = generateVoterToken(voterEmail);
veeRepo.save(tokenedVoter);
return tokenedVoter;

    }
    public Voter generateVoterToken(Voter voter){
        String Fl = pickFirstTwoAlphabet(voter.getFirstName());
        String LL = pickLastTwoAlphabet(voter.getVoterAddress().getLocalGovernment());
        String number = numberGenerator();
        String token = LL+number+Fl;
        voter.setVoteNumber(token);
        return voter;
    }
    public Voter buildVoter(VoterRequest voterRequest) throws VoterException {
    return Voter.builder()
    .firstName(voterRequest.getFirstName()).
    lastName(voterRequest.getLastName())
    .password(voterRequest.getPassword())
    .phoneNumber(voterRequest.getPhoneNumber())
    .userEmailAddress(voterRequest.getUserEmailAddress())
    .voterAddress(voterRequest.getVoterAddress())
    .occupation(voterRequest.getOccupation())
    .gender(voterRequest.getGender()).build();

    }
    public String pickFirstTwoAlphabet(String word){
        String Li = "";
        for (int i = 0; i < 2; i++) {
         Li  = String.valueOf( word.charAt(i));
        }
      return Li;
    }
    public String numberGenerator() {
        SecureRandom secureRandom = new SecureRandom();
        int number = secureRandom.nextInt(50000,99000);
        return String.valueOf(number);
}
   public String pickLastTwoAlphabet(String word){
        String Li = "";
       for (int i = word.length()-2; i < 0; i++) {
        Li = String.valueOf(word.charAt(i));
       }
       return Li;
   }
   public Voter sendRegistationMail(Voter voter){
       String emailHeader = "E-VOTER REGISTERATION SUCCESSFUL";
       String emailBody = "welcome to the online voting system\n\n" +
               "OUT GOAL \n" +
               "we're here for you to make voting easier" +
               " and safer, with our online voting system\n" +
               "YOUR VOTE COUNTTS";
 return emailService.sendEmail(voter,emailBody,emailHeader);

    }


}
