package com.ges.banqueservice.web;

import com.ges.banqueservice.dto.AgenceDTO;
import com.ges.banqueservice.dto.BanqueDTO;
import com.ges.banqueservice.dto.CompteBancaireDTO;
import com.ges.banqueservice.entities.Agence;
import com.ges.banqueservice.entities.Banque;
import com.ges.banqueservice.entities.CompteBancaire;
import com.ges.banqueservice.repository.AgenceRepository;
import com.ges.banqueservice.repository.BanqueRepository;
import com.ges.banqueservice.repository.CompteBancaireRepository;
import com.ges.banqueservice.service.PlanComptableRestClientService;
import com.ges.banqueservice.service.SocieteRestClientService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CompteBancaireGraphQLController {
    private AgenceRepository agenceRepository;
    private CompteBancaireRepository compteBancaireRepository;
    private BanqueRepository banqueRepository;
    private PlanComptableRestClientService planComptableRestClientService;
    private SocieteRestClientService societeRestClientService;

    public CompteBancaireGraphQLController(AgenceRepository agenceRepository, CompteBancaireRepository compteBancaireRepository,
                                           BanqueRepository banqueRepository,
                                           PlanComptableRestClientService planComptableRestClientService, SocieteRestClientService societeRestClientService) {
        this.agenceRepository = agenceRepository;
        this.compteBancaireRepository = compteBancaireRepository;
        this.banqueRepository = banqueRepository;
        this.planComptableRestClientService = planComptableRestClientService;
        this.societeRestClientService = societeRestClientService;
    }
    @QueryMapping
    public List<CompteBancaire> compteBancaireList(){
        List<CompteBancaire> compteBancaireList=compteBancaireRepository.findAll();
//        for (int i=0;i<compteBancaireList.size();i++){
//            compteBancaireList.get(i).setPlanComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaireList.get(i).getPlanComptableElementId()));
////            compteBancaireList.get(i).setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaireList.get(i).getSocieteId()));
//            System.out.println(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaireList.get(i).getSocieteId()));
//        }
        return compteBancaireList;
    }
    @QueryMapping
    public List<CompteBancaire> compteBancaireListByBanqueId(@Argument String id){
        List<CompteBancaire> compteBancaireList=compteBancaireRepository.findAll();
        List<CompteBancaire> compteBancairesByBanqueId=new ArrayList<>();
        for (int i=0;i<compteBancaireList.size();i++){
            if(compteBancaireList.get(i).getBanque().getId().equals(id)){
                compteBancairesByBanqueId.add(compteBancaireList.get(i));
            }
        }
        return compteBancairesByBanqueId;
    }
    @QueryMapping
    public CompteBancaire compteBancaireById(@Argument String id){
        CompteBancaire compteBancaire=compteBancaireRepository.findById(id).get();
        compteBancaire.setPlanComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaire.getPlanComptableElementId()));
//        compteBancaire.setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaire.getSocieteId()));
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
                .planComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaireDTO.getPlanComptableElementId()))
//                .societeId(compteBancaireDTO.getSocieteId())
//                .societe(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaireDTO.getSocieteId()))
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
        compteBancaire.setPlanComptableElement(compteBancaireDTO.getPlanComptableElementId()==null?compteBancaire.getPlanComptableElement():planComptableRestClientService.planComptableElementById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaireDTO.getPlanComptableElementId()));
//        compteBancaire.setSocieteId(compteBancaireDTO.getSocieteId()==null?compteBancaire.getSocieteId():compteBancaireDTO.getSocieteId());
//        compteBancaire.setSociete(compteBancaireDTO.getSocieteId()==null?compteBancaire.getSociete():societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaire.getSocieteId()));
        return compteBancaireRepository.save(compteBancaire);
    }
    @MutationMapping
    public CompteBancaire supprimerCompteBancaire(@Argument String id){
        CompteBancaire compteBancaire=compteBancaireRepository.findById(id).get();
        compteBancaireRepository.delete(compteBancaire);
        return compteBancaire;
    }

    @MutationMapping
    public CompteBancaire ajouterCompteBancaireBanqueAgence(@Argument AgenceDTO agenceDTO, @Argument BanqueDTO banqueDTO, @Argument CompteBancaireDTO compteBancaireDTO){
        Agence agence=Agence.builder()
                .id(UUID.randomUUID().toString())
                .nom(agenceDTO.getNom())
                .complement(agenceDTO.getComplement())
                .code_postale(agenceDTO.getCode_postale())
                .ville(agenceDTO.getVille())
                .pays(agenceDTO.getPays())
                .build();
        Agence agenceSaved= agenceRepository.save(agence);
        Banque banque=Banque.builder()
                .id(UUID.randomUUID().toString())
                .abrege(banqueDTO.getAbrege())
                .intitule(banqueDTO.getIntitule())
                .interlocuteur(banqueDTO.getInterlocuteur())
                .codeBIC(banqueDTO.getCodeBIC())
                .adresse(banqueDTO.getAdresse())
                .code_postale(banqueDTO.getCode_postale())
                .ville(banqueDTO.getVille())
                .pays(banqueDTO.getPays())
                .telephone(banqueDTO.getTelephone())
                .telecopie(banqueDTO.getTelecopie())
                .email(banqueDTO.getEmail())
                .site_internet(banqueDTO.getSite_internet())
                .agence(agenceSaved)
                .build();
        Banque banqueSaved= banqueRepository.save(banque);
        CompteBancaire compteBancaire=CompteBancaire.builder()
                .id(UUID.randomUUID().toString())
                .abrege(compteBancaireDTO.getAbrege())
                .devise(compteBancaireDTO.getDevise())
                .pays(compteBancaireDTO.getPays())
                .structure(compteBancaireDTO.getStructure())
                .num_compte(compteBancaireDTO.getNum_compte())
                .num_guichet(compteBancaireDTO.getNum_guichet())
                .banque(banqueSaved)
                .planComptableElementId(compteBancaireDTO.getPlanComptableElementId())
                .planComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaireDTO.getPlanComptableElementId()))
//                .societeId(compteBancaireDTO.getSocieteId())
//                .societe(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),compteBancaireDTO.getSocieteId()))
                .build();
        return compteBancaireRepository.save(compteBancaire);
    }
}
