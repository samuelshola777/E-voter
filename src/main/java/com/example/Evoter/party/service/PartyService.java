package com.example.Evoter.party.service;

import com.example.Evoter.dto.request.PartyRequest;
import com.example.Evoter.dto.response.PartyResponse;
import com.example.Evoter.party.data.model.Party;
import com.example.Evoter.voter.exception.PartyException;
import com.example.Evoter.voter.exception.PartyRegistrationException;

public interface PartyService {


    String registerParty(PartyRequest partyRequest1) throws PartyRegistrationException;

    Party findPartyById(long id) throws PartyException;

    String deletePartyById(long i);

    long countRegisteredParty();
}
