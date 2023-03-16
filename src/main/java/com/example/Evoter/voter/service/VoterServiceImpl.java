package com.example.Evoter.voter.service;

//import com.example.Evoter.cloud.CloudInterface;
import com.example.Evoter.dto.request.PasswordRequest;
import com.example.Evoter.emailSender.emailService.EmailServiceImpl;
import com.example.Evoter.voter.data.model.Gender;
import com.example.Evoter.voter.data.model.Voter;
import com.example.Evoter.voter.data.repository.VoterRepository;
import com.example.Evoter.dto.request.VoterRequest;
import com.example.Evoter.voter.exception.PasswordExeption;
import com.example.Evoter.voter.exception.VoterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class VoterServiceImpl implements VoterService{
//@Autowired
//CloudInterface cloudInterface;
@Autowired
EmailServiceImpl emailService;
@Autowired
VoterRepository veeRepo;

@Override
public Voter createVoteAccount(VoterRequest voterRequest) throws VoterException {
Voter builtVoter = buildVoter(voterRequest);
Voter tokenedVoter = generateVoterToken(builtVoter);
if (!findByEmail(tokenedVoter.getUserEmailAddress())) throw new VoterException("Account Already Exist ");
Voter voterEmail = sendRegistrationMail(tokenedVoter);
veeRepo.save(voterEmail);
return voterEmail;
}

@Override
public Voter findByVoterById(long i) throws VoterException {
return veeRepo.findById(i).
orElseThrow(()-> new VoterException
("Voter with the ID "+i+" does not exist"));
}

@Override
public Voter findByVoterByEmail(String emailAddress) throws VoterException {
Voter voter = veeRepo.findByUserEmailAddress(emailAddress);
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
veeRepo.save(foundVoter);
return passwordRequest.getNewPassword();
}

    @Override
    public String forgotPassword(PasswordRequest passwordRequest) throws PasswordExeption, VoterException {
    Voter forgetPassWordVoter = findByVoterByEmail(passwordRequest.getUserEmailAddress());
    if (!forgetPassWordVoter.getVoteNumber().equals(passwordRequest.getVoteNumber()))
throw new PasswordExeption("you have entered a wrong vote number trying to retrieve password");
    if (!forgetPassWordVoter.getPhoneNumber().equals(passwordRequest.getPhoneNumber()))
        throw new PasswordExeption("the phone Number you entered is in valid");
    String newPassword = passwordGenerator(forgetPassWordVoter);
    forgetPassWordVoter.setPassword(newPassword);
        return "GOOD DAY "+forgetPassWordVoter.getFirstName()+" \n" +
    "the system have generated a new password and sent it the email address "+forgetPassWordVoter.getUserEmailAddress();
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
String Li = word.substring( 0,2);
return Li;
}
public String numberGenerator() {
SecureRandom secureRandom = new SecureRandom();
int number = secureRandom.nextInt(50000,99000);
return String.valueOf(number);
}
public String pickLastTwoAlphabet(String word){
String Li = word.substring(word.length() - 2);

return Li;
}

public boolean findByEmail(String email){
if (veeRepo.findByUserEmailAddress(email) == null)return true;
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
public String passwordGenerator(Voter voter){
    SecureRandom secureRandom = new SecureRandom();
    int num = secureRandom.nextInt(700000,9000000);
    String fFirst = pickFirstTwoAlphabet(voter.getLastName());
    String lLastTwo = pickLastTwoAlphabet(voter.getFirstName());
    return lLastTwo+num+fFirst;
}



}
