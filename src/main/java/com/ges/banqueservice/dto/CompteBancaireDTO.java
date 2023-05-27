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
public class CompteBancaireDTO {
    private String abrege;
    private String devise;
    private String pays;
    private String structure;
    private String num_compte;
    private String num_guichet;
    private String banqueId;
}
