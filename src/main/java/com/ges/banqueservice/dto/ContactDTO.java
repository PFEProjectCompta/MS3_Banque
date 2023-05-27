package com.ges.banqueservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ges.banqueservice.entities.Banque;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class ContactDTO {
    private String type_contact;
    private String civilite;
    private String nom;
    private String prenom;
    private String service;
    private String fonction;
    private String telephone;
    private String portable;
    private String email;
    private String telecopie;
    private String banqueId;
}
