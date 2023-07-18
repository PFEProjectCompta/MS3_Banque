package com.ges.banqueservice.web;

import com.ges.banqueservice.config.InitialData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BanqueController {
    @GetMapping("/loadData")
    public String loadData(){
        InitialData.ajouterAgence();
        InitialData.ajouterBanque();
        InitialData.ajouterCompteBancaire();
        InitialData.ajoutercontact();
        return "Done";
    }
}
