package com.ges.banqueservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToMany(mappedBy = "banque",cascade = CascadeType.REMOVE)
    private List<Contact> contacts;
    @OneToMany(mappedBy = "banque", cascade = CascadeType.REMOVE)
    private List<CompteBancaire> compteBancaires;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "agence")
    private Agence agence;
}
