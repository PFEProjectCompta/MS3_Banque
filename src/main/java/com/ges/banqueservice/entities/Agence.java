package com.ges.banqueservice.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Agence {
    @Id
    private String id;
    private String nom;
    private String complement;
    private String code_postale;
    private String ville;
    private String pays;
    private String societeId;
    @OneToMany(mappedBy = "agence",cascade = CascadeType.REMOVE)
    private List<Banque> banques;

}
