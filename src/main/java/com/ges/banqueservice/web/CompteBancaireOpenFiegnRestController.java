package com.ges.banqueservice.web;

import com.ges.banqueservice.model.PlanComptableElement;
import com.ges.banqueservice.service.PlanComptableRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompteBancaireOpenFiegnRestController {
    @Autowired
    private PlanComptableRestClientService planComptableRestClientService;
    @GetMapping("/planComptableElements")
    public List<PlanComptableElement> planComptableElements(){
        return planComptableRestClientService.allplanComptableElements().getContent().stream().toList();
    }
    @GetMapping("/planComptableElements/{id}")
    public PlanComptableElement planComptableElementById(@PathVariable String id){
        return planComptableRestClientService.planComptableElementById(id);
    }
}
