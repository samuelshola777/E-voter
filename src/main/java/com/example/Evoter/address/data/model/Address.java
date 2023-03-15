package com.example.Evoter.address.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String houseNumber;
    private String StreetName;
    private String localGovernment;
    private String state;

    public void setLocalGovernment(String localGovernment) {
        String completeLocal = " local Government";
                if (!localGovernment.contains(completeLocal)) {
                    localGovernment = localGovernment + completeLocal;
                }
        this.localGovernment = localGovernment;
    }



}
