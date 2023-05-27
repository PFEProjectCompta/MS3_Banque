package com.ges.banqueservice.dto;

import com.ges.banqueservice.entities.Banque;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class AgenceDTO {
    private String nom;
    private String complement;
    private String code_postale;
    private String ville;
    private String pays;
}
