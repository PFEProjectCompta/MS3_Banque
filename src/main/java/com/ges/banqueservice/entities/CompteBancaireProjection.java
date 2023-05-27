package com.ges.banqueservice.entities;

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
}
