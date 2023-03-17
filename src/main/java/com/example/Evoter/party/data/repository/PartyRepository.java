package com.example.Evoter.party.data.repository;

import com.example.Evoter.party.data.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party,Long> {
}
