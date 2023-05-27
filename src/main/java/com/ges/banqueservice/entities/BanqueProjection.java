package com.ges.banqueservice.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "banqueProjection",types = Banque.class)
public interface BanqueProjection {
    public String getId();
    public String getVille();
    public String getPays();
    public String getCode_postale();
    public String getAbrege();
    public String getAdresse();
    public String getCodeBIC();
    public String getEmail();
    public String getInterlocuteur();
    public String getIntitule();
    public String getSite_internet();
    public String getTelecopie();
    public String getTelephone();
    public List<Contact> getContacts();
    public List<CompteBancaire> getCompteBancaires();
    public Agence getAgence();
}
