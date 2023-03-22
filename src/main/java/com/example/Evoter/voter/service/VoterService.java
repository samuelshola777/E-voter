package com.example.Evoter.voter.service;

import com.example.Evoter.dto.request.PasswordRequest;
import com.example.Evoter.dto.response.VoterResponse;
import com.example.Evoter.voter.data.model.Voter;
import com.example.Evoter.dto.request.VoterRequest;
import com.example.Evoter.voter.exception.PartyRegistrationException;
import com.example.Evoter.voter.exception.PasswordExeption;
import com.example.Evoter.voter.exception.VoterException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VoterService {
    VoterResponse createVoteAccount(VoterRequest voter1) throws VoterException, PartyRegistrationException;

    Voter buildVoter(VoterRequest voterRequest);
    Voter findByVoterById(long i) throws VoterException;

    Voter findByVoterByEmail(String s) throws VoterException;

    String changePassword(PasswordRequest passwordRequest) throws VoterException;

    String forgotPassword(PasswordRequest passwordRequest) throws VoterException, PasswordExeption;
    Page<VoterResponse> getAllVoters(int pageNumber);

    String deleteVoterById(long id);

    long countNumberOfVoters();
}
