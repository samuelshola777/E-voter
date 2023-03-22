package com.example.Evoter.voter.service;

//import com.example.Evoter.cloud.CloudInterface;
import com.example.Evoter.dto.request.PasswordRequest;
import com.example.Evoter.dto.response.VoterResponse;
import com.example.Evoter.emailSender.emailService.EmailServiceImpl;
import com.example.Evoter.voter.data.model.Gender;
import com.example.Evoter.voter.data.model.Voter;
import com.example.Evoter.voter.data.repository.VoterRepository;
import com.example.Evoter.dto.request.VoterRequest;
import com.example.Evoter.voter.exception.PartyRegistrationException;
import com.example.Evoter.voter.exception.PasswordExeption;
import com.example.Evoter.voter.exception.VoterException;
import com.example.Evoter.workToolsservice.ToolService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class VoterServiceImpl implements VoterService{
//@Autowired
//CloudInterface cloudInterface;


private final EmailServiceImpl emailService;

private final VoterRepository voterRepository;
ToolService tool = new ToolService();

@Override
public VoterResponse createVoteAccount(VoterRequest voterRequest) throws VoterException, PartyRegistrationException {
Voter builtVoter = buildVoter(voterRequest);
verifyPhoneNumberLength(builtVoter.getPhoneNumber());
Voter tokenVoter = tool.generateVoterToken(builtVoter);
verifyIfPhoneNumberContainsAlphabet(builtVoter.getPhoneNumber());
if (!findByEmail(tokenVoter.getUserEmailAddress())) throw new VoterException("Account Already Exist ");
Voter voterEmail = sendRegistrationMail(tokenVoter);
voterRepository.save(voterEmail);
    return mapFromVoterToResponse(voterEmail);

}

@Override
public Voter findByVoterById(long i) throws VoterException {
return voterRepository.findById(i).
orElseThrow(()-> new VoterException
("Voter with the ID "+i+" does not exist"));
}

@Override
public Voter findByVoterByEmail(String emailAddress) throws VoterException {
Voter voter = voterRepository.findByUserEmailAddress(emailAddress);
if (voter == null) throw new VoterException("no Existing Account with such email address");
return voter;
}

@Override
public String changePassword(PasswordRequest passwordRequest) throws VoterException {
Voter foundVoter = findByVoterByEmail(passwordRequest.getUserEmailAddress());
if (foundVoter == null) throw new VoterException
("no Existing with the  Email address "+passwordRequest.getUserEmailAddress());
if (!foundVoter.getVoteNumber().equals(passwordRequest.getVoteNumber()))
throw new VoterException("the vote number "+passwordRequest.getVoteNumber()+" is not recognised");
changePasswordMailSender(foundVoter, passwordRequest.getNewPassword());
foundVoter.setPassword(passwordRequest.getNewPassword());
voterRepository.save(foundVoter);
return passwordRequest.getNewPassword();
}

    @Override
public String forgotPassword(PasswordRequest passwordRequest) throws PasswordExeption, VoterException {
Voter forgetPassWordVoter = findByVoterByEmail(passwordRequest.getUserEmailAddress());
if (!forgetPassWordVoter.getVoteNumber().equals(passwordRequest.getVoteNumber()))
throw new PasswordExeption("you have entered a wrong vote number trying to retrieve password");
String newPassword = tool.passwordGenerator(forgetPassWordVoter);
forgetPassWordVoter.setPassword(newPassword);
forgetPasswordMailSender(newPassword,forgetPassWordVoter);
return "GOOD DAY "+forgetPassWordVoter.getFirstName()+" \n" +
"the system have generated a new password and sent it the email address " +
""+forgetPassWordVoter.getUserEmailAddress();
    }

    @Override
    public String deleteVoterById(long id) {
        voterRepository.deleteById(id);
        return "voter with the ID ()->  " + id + "  is successfully deleted";
    }

    @Override
    public long countNumberOfVoters() {
        return voterRepository.count();
    }

    public Voter buildVoter(VoterRequest voterRequest) {
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

public boolean findByEmail(String email){
if (voterRepository.findByUserEmailAddress(email) == null)return true;
return false;
}
public Voter sendRegistrationMail(Voter voter){
String emailHeader = "E-VOTER REGISTERATION SUCCESSFUL";
String emailBody = "welcome to the online voting system\n\n" +
       "OUT GOAL \n" +
       "we're here for you to make voting easier" +
       " and safer, with our online voting system\n" +
       "YOUR VOTE COUNTTS";
return emailService.sendEmail(voter,emailHeader,emailBody);

}
public void changePasswordMailSender(Voter voter, String newPassword){
String changePasswordHeader = "E-VOTING APP CHANGING OF CHANGING OF PASSWORD NOTIFICATION ";
String changePasswordMessage = "GOOD DAY MR  "+voter.getFirstName()+" "+voter.getLastName()+" \n" +
"hope you're having a nice day.\n" +
"this is to inform your that you E-VOTI" +
"NG app account that was built by SAMUEL-SHOLA " +
"password has been changed from "+voter.getPassword()+" to "+newPassword;
    String changePasswordMessageFemale = "GOOD DAY MISS/MRS  "+voter.getFirstName()+" "+voter.getLastName()+" \n" +
            "hope you're having a nice day.\n" +
            "this is to inform your that you E-VOTI" +
            "NG app account that was built by SAMUEL-SHOLA " +
            "password has been changed from "+voter.getPassword()+" to "+newPassword;
if (voter.getGender() == Gender.FEMALE) emailService.sendEmail(voter,changePasswordHeader,changePasswordMessageFemale);
if (voter.getGender() == Gender.MALE) emailService.sendEmail(voter,changePasswordHeader,changePasswordMessage);

}
public void forgetPasswordMailSender(String newPassword, Voter voter){
    LocalTime changeTime = LocalTime.now();
    LocalDate changeDate = LocalDate.now();

    String header ="<<E-VOTERS  FORGET PASSWORD NOTIFICATION>>";
    String body = "good day "+voter.getFirstName()+"" +
    " "+voter.getLastName()+" this is to inform you that" +
" a new password has been create for you " +"\n password" +
" Generated time  ====>>> "+changeTime+" \n password Generated date ====>>> "+changeDate+" \n"
    +" E-VOTER account ()=====>>> "+newPassword+"  is your  " +
"new password please ensure to change your password to what you can remember ";
    emailService.sendEmail(voter,header,body);
}
public void verifyPhoneNumberLength(String phoneNumber) throws PartyRegistrationException, VoterException {
        if (!tool.verifyPhoneNumberLength(phoneNumber))
            throw new VoterException("you have entered an invalid length phone number");
    }

public void verifyIfPhoneNumberContainsAlphabet(String phoneNumber) throws VoterException {
    if (tool.phoneNumberVerifier(phoneNumber))
        throw new VoterException("you have entered an invalid phone number");
}

    public VoterResponse mapFromVoterToResponse(Voter voter){
     VoterResponse voterResponse = VoterResponse.
             builder().firstName(voter.getFirstName())
             .lastName(voter.getLastName()).
             gender(voter.getGender()).
             userEmailAddress(voter.getUserEmailAddress())
             .build();
     return voterResponse;
    }

    public Page<VoterResponse> getAllVoters(int pageNumber){
        ModelMapper mapper = new ModelMapper();
    Pageable pageable = PageRequest.of(pageNumber, 3);
        Page<Voter> voter = voterRepository.findAll(pageable);
        List<Voter> voterList = voter.getContent();

        List<VoterResponse> response = voter.getContent().stream()
                .map(voters -> mapper.map(voterList, VoterResponse.class))
                .collect(Collectors.toList());
        return new PageImpl(response, pageable, voter.getTotalElements());
    }

}
