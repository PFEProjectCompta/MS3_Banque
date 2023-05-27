package com.ges.banqueservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "contactProjection",types = Contact.class)
public interface ContactProjection {
    public String getId();
    public String getType_contact();
    public String getCivilite();
    public String getNom();
    public String getPrenom();
    public String getService();
    public String getFonction();
    public String getTelecopie();
    public String getTelephone();
    public String getEmail();
    public String getPortable();
    public Banque getBanque();
}
