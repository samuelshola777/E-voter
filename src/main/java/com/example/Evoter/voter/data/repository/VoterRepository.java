package com.example.Evoter.voter.data.repository;

import com.example.Evoter.voter.data.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}
