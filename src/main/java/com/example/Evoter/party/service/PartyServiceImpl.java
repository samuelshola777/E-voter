package com.example.Evoter.party.service;

import com.example.Evoter.dto.request.PartyRequest;
import com.example.Evoter.dto.response.PartyResponse;
import com.example.Evoter.party.data.model.Party;
import com.example.Evoter.party.data.repository.PartyRepository;
import com.example.Evoter.voter.exception.PartyException;
import com.example.Evoter.voter.exception.PartyRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyServiceImpl implements PartyService{
    @Autowired
    PartyRepository partyRepository;
    @Override
    public String registerParty(PartyRequest partyRequest1) throws PartyRegistrationException {
        Party party = partyMapper(partyRequest1);
        if (findPartyByName(party.getPartyName() ) != null)
        throw new PartyRegistrationException("Party name ()-> " +
        party.getPartyName() +" already exist");
        if (findPartyByEmailAddress(party.getPartyEmailAddress() ) != null)
        throw new PartyRegistrationException("Party email ()-> " +
            party.getPartyEmailAddress() +" already exist");
        partyRepository.save(party);
        return "party registration completed successfully";
    }

    @Override
    public Party findPartyById(long id) throws PartyException {
      Party party = findPartyById(id);
      if (party == null) throw new PartyException("Could not find party with id " + id);
          return party;
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
}
