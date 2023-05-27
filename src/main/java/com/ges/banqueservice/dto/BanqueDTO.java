package com.ges.banqueservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ges.banqueservice.entities.Agence;
import com.ges.banqueservice.entities.CompteBancaire;
import com.ges.banqueservice.entities.Contact;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor@AllArgsConstructor @Builder
public class BanqueDTO {
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
    private String agenceId;
}
