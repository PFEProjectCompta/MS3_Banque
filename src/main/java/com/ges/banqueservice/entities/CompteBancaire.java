package com.ges.banqueservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "compte_bancaire")
public class CompteBancaire {
    @Id
    private String id;
    private String abrege;
    private String devise;
    private String pays;
    private String structure;
    private String num_compte;
    private String num_guichet;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="banque")
    private Banque banque;
}
