package com.ges.banqueservice.web;

import com.ges.banqueservice.dto.CompteBancaireDTO;
import com.ges.banqueservice.entities.CompteBancaire;
import com.ges.banqueservice.repository.BanqueRepository;
import com.ges.banqueservice.repository.CompteBancaireRepository;
import com.ges.banqueservice.service.PlanComptableRestClientService;
import com.ges.banqueservice.service.SocieteRestClientService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class CompteBancaireGraphQLController {
    private CompteBancaireRepository compteBancaireRepository;
    private BanqueRepository banqueRepository;
    private PlanComptableRestClientService planComptableRestClientService;
    private SocieteRestClientService societeRestClientService;

    public CompteBancaireGraphQLController(CompteBancaireRepository compteBancaireRepository,
                                           BanqueRepository banqueRepository,
                                           PlanComptableRestClientService planComptableRestClientService, SocieteRestClientService societeRestClientService) {
        this.compteBancaireRepository = compteBancaireRepository;
        this.banqueRepository = banqueRepository;
        this.planComptableRestClientService = planComptableRestClientService;
        this.societeRestClientService = societeRestClientService;
    }
    @QueryMapping
    public List<CompteBancaire> compteBancaireList(){
        List<CompteBancaire> compteBancaireList=compteBancaireRepository.findAll();
        for (int i=0;i<compteBancaireList.size();i++){
            compteBancaireList.get(i).setPlanComptableElement(planComptableRestClientService.planComptableElementById(compteBancaireList.get(i).getPlanComptableElementId()));
            compteBancaireList.get(i).setSociete(societeRestClientService.SocieteById(compteBancaireList.get(i).getSocieteId()));
            System.out.println(societeRestClientService.SocieteById(compteBancaireList.get(i).getSocieteId()));
        }
        return compteBancaireList;
    }
    @QueryMapping
    public CompteBancaire compteBancaireById(@Argument String id){
        CompteBancaire compteBancaire=compteBancaireRepository.findById(id).get();
        compteBancaire.setPlanComptableElement(planComptableRestClientService.planComptableElementById(compteBancaire.getPlanComptableElementId()));
        compteBancaire.setSociete(societeRestClientService.SocieteById(compteBancaire.getSocieteId()));
        return compteBancaire;
    }
    @MutationMapping
    public CompteBancaire ajouterCompteBancaire(@Argument CompteBancaireDTO compteBancaireDTO){
        CompteBancaire compteBancaire=CompteBancaire.builder()
                .id(UUID.randomUUID().toString())
                .abrege(compteBancaireDTO.getAbrege())
                .devise(compteBancaireDTO.getDevise())
                .pays(compteBancaireDTO.getPays())
                .structure(compteBancaireDTO.getStructure())
                .num_compte(compteBancaireDTO.getNum_compte())
                .num_guichet(compteBancaireDTO.getNum_guichet())
                .banque(banqueRepository.findById(compteBancaireDTO.getBanqueId()).get())
                .planComptableElementId(compteBancaireDTO.getPlanComptableElementId())
                .planComptableElement(planComptableRestClientService.planComptableElementById(compteBancaireDTO.getPlanComptableElementId()))
                .societeId(compteBancaireDTO.getSocieteId())
                .societe(societeRestClientService.SocieteById(compteBancaireDTO.getSocieteId()))
                .build();
        return compteBancaireRepository.save(compteBancaire);
    }
    @MutationMapping
    public CompteBancaire modifierCompteBancaire(@Argument CompteBancaireDTO compteBancaireDTO,@Argument String id){
        CompteBancaire compteBancaire=compteBancaireRepository.findById(id).get();
        compteBancaire.setAbrege(compteBancaireDTO.getAbrege()==null?compteBancaire.getAbrege():compteBancaireDTO.getAbrege());
        compteBancaire.setDevise(compteBancaireDTO.getDevise()==null? compteBancaire.getDevise():compteBancaireDTO.getDevise());
        compteBancaire.setPays(compteBancaireDTO.getPays()==null? compteBancaire.getPays():compteBancaireDTO.getPays());
        compteBancaire.setStructure(compteBancaireDTO.getStructure()==null? compteBancaire.getStructure():compteBancaireDTO.getStructure());
        compteBancaire.setNum_compte(compteBancaireDTO.getNum_compte()==null?compteBancaire.getNum_compte():compteBancaireDTO.getNum_compte());
        compteBancaire.setNum_guichet(compteBancaireDTO.getNum_guichet()==null?compteBancaire.getNum_guichet():compteBancaireDTO.getNum_guichet());
        compteBancaire.setBanque(compteBancaireDTO.getBanqueId()==null?compteBancaire.getBanque():banqueRepository.findById(compteBancaireDTO.getBanqueId()).get());
        compteBancaire.setPlanComptableElementId(compteBancaireDTO.getPlanComptableElementId()==null? compteBancaire.getPlanComptableElementId():compteBancaireDTO.getPlanComptableElementId());
        compteBancaire.setPlanComptableElement(compteBancaireDTO.getPlanComptableElementId()==null?compteBancaire.getPlanComptableElement():planComptableRestClientService.planComptableElementById(compteBancaireDTO.getPlanComptableElementId()));
        compteBancaire.setSocieteId(compteBancaireDTO.getSocieteId()==null?compteBancaire.getSocieteId():compteBancaireDTO.getSocieteId());
        compteBancaire.setSociete(compteBancaireDTO.getSocieteId()==null?compteBancaire.getSociete():societeRestClientService.SocieteById(compteBancaire.getSocieteId()));
        return compteBancaireRepository.save(compteBancaire);
    }
    @MutationMapping
    public CompteBancaire supprimerCompteBancaire(@Argument String id){
        CompteBancaire compteBancaire=compteBancaireRepository.findById(id).get();
        compteBancaireRepository.delete(compteBancaire);
        return compteBancaire;
    }
}
