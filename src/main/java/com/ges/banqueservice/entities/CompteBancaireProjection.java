package com.ges.banqueservice.entities;

import com.ges.banqueservice.model.PlanComptableElement;
import com.ges.banqueservice.model.Societe;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "compteBancaireProjection", types = CompteBancaire.class)
public interface CompteBancaireProjection {
    public String getId();
    public String getAbrege();
    public String getDevise();
    public String getPays();
    public String getStructure();
    public String getNum_compte();
    public String getNum_guichet();
    public Banque getBanque();
    public String getPlanComptableElementId();
    public PlanComptableElement getPlanComptableElement();
    public String getSocieteId();
    public Societe getSociete();
}
