package com.ges.banqueservice.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "agenceProjection", types = Agence.class)
public interface AgenceProjection {
    public String getId();
    public String getComplement();
    public String getVille();
    public String getPays();
    public String getCode_postale();
    public String getNom();
    public List<Banque> getBanques();
}
