package com.ges.banqueservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Contact {
    @Id
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
}
