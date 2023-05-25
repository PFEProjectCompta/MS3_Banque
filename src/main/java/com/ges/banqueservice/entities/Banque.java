package com.ges.banqueservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "banque")
public class Banque {
    @Id
    private String id;
    private String abrege;
    private String intitule;
    private String interlocuteur;
    private String codeBIC;
    private String adresse;
    private String code_postale;
    private String ville;
    private String pays;
    private String telephone;
    private String telecopie;
    private String email;
    private String site_internet;
}
