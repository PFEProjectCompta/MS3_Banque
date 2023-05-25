package com.ges.banqueservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Agence {
    @Id
    private String id;
    private String nom;
    private String complent;
    private String code_postale;
    private String ville;
    private String pays;
}
