package com.ges.banqueservice.model;

import com.ges.banqueservice.enums.NatureCompte;
import lombok.Data;

@Data
public class CompteGeneral {
    private String id;
    private NatureCompte natureCompte;
    private String debutFaurchette;
    private String finFaurchette;
}
