package com.example.Evoter.voter.service;

import com.example.Evoter.voter.data.model.Voter;
import com.example.Evoter.voter.dto.request.VoterRequest;
import com.example.Evoter.voter.exception.VoterException;

import java.util.Optional;

public interface VoterService {
    Voter createVoteAccount(VoterRequest voter1) throws VoterException;


    Voter findByVoterById(long i) throws VoterException;

    Voter findByVoterByEmail(String s) throws VoterException;

    String changePassword(String s, String nt61496nn, String newPassword) throws VoterException;
}
