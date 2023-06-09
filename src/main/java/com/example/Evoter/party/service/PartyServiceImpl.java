package com.example.Evoter.party.service;

import com.example.Evoter.dto.request.PartyRequest;
import com.example.Evoter.party.data.model.Party;
import com.example.Evoter.party.data.repository.PartyRepository;
import com.example.Evoter.voter.exception.PartyException;
import com.example.Evoter.voter.exception.PartyRegistrationException;
import com.example.Evoter.voter.exception.VoterException;
import com.example.Evoter.workToolsservice.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyServiceImpl implements PartyService{
    @Autowired
    PartyRepository partyRepository;
    ToolService tool = new ToolService();
    @Override
    public String registerParty(PartyRequest partyRequest1) throws PartyRegistrationException, VoterException {
        Party party = partyMapper(partyRequest1);
        verifyIfPhoneNumberContainAlphabet(party.getPartyPhoneNumber());
        checkIfAccountAlreadyExists(party);
        verifyPhoneNumberLength(party.getPartyPhoneNumber());
        partyRepository.save(party);
        return "party registration completed successfully";
    }

    @Override
    public Party findPartyById(long id) throws PartyException {
      Party party = partyRepository.findPartyById(id);
      if (party == null) throw new PartyException("Could not find party with id " + id);
          return party;
    }

    @Override
    public String deletePartyById(long i) {
        partyRepository.deleteById(i);
         return "party deleted successfully";

    }

    @Override
    public long countRegisteredParty() {
        return partyRepository.count();
    }

    public Party partyMapper(PartyRequest partyRequest){
        Party party = Party.builder().
                partyName(partyRequest.getPartyName())
                .partyPhoneNumber(partyRequest.getPartyPhoneNumber())
                .partyChairMan(partyRequest.getPartyChairMan())
                .partyEmailAddress(partyRequest.getPartyEmailAddress())
                .headOfficeAddress(partyRequest.getHeadOfficeAddress())
                .candidateName(partyRequest.getCandidateName())
                .build();
        return party;
    }
    public Party findPartyByName(String partyName){
       return partyRepository.findPartyByPartyName(partyName);
    }
    public Party findPartyByEmailAddress(String emailAddress){
        return partyRepository.findPartyByPartyEmailAddress(emailAddress);
    }
public void verifyIfPhoneNumberContainAlphabet(String phoneNumber) throws VoterException {
    if (tool.phoneNumberVerifier(phoneNumber)) throw new VoterException("you have entered an invalid phone number");
}
public void checkIfAccountAlreadyExists(Party party) throws PartyRegistrationException {
if ( findPartyByName(party.getPartyName())
!= null && findPartyByEmailAddress(party.getPartyEmailAddress()) != null )
throw new PartyRegistrationException("party account already exist");
}
public void verifyPhoneNumberLength(String phoneNumber) throws PartyRegistrationException {
        if (!tool.verifyPhoneNumberLength(phoneNumber))
        throw new PartyRegistrationException("you have entered an invalid length phone number");
}

public Party findByPartyName(String partyName) throws PartyException {
Party party = partyRepository.findPartyByPartyName(partyName);
if (party == null) throw new PartyException
("no registered party with the party name  "+partyName+" found");
return party;
    }
}
