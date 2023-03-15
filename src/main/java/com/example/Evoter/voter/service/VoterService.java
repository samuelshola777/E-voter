package com.example.Evoter.voter.service;

import com.example.Evoter.voter.data.model.Voter;
import com.example.Evoter.voter.dto.request.VoterRequest;
import com.example.Evoter.voter.dto.response.VoterResponse;
import com.example.Evoter.voter.exception.VoterException;
import org.springframework.web.multipart.MultipartFile;

public interface VoterService {
    Voter createVoteAccount(VoterRequest voter1) throws VoterException;


}
