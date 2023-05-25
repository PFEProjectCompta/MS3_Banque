package com.ges.banqueservice.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CompteBancaire {
    private String id;
    private String abrege;
    private String devise;
    private String pays;
    private String structure;
    private String num_compte;
    private Banque banque;
    private String num_guichet;
}
