package com.ges.banqueservice.web;

import com.ges.banqueservice.dto.BanqueDTO;
import com.ges.banqueservice.entities.Banque;
import com.ges.banqueservice.repository.AgenceRepository;
import com.ges.banqueservice.repository.BanqueRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class BanqueGraphQLController {
    private BanqueRepository banqueRepository;
    private AgenceRepository  agenceRepository;

    public BanqueGraphQLController(BanqueRepository banqueRepository, AgenceRepository agenceRepository) {
        this.banqueRepository = banqueRepository;
        this.agenceRepository = agenceRepository;
    }

    @QueryMapping
    public List<Banque> banqueList(){
        return banqueRepository.findAll();
    }
    @QueryMapping
    public Banque banqueById(@Argument String id){
        return banqueRepository.findById(id).get();
    }
    @MutationMapping
    public Banque ajouterBanque(@Argument BanqueDTO banqueDTO){
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
                .agence(agenceRepository.findById(banqueDTO.getAgenceId()).get())
                .build();
        return banqueRepository.save(banque);
    }
    @MutationMapping
    public Banque modifierBanque(@Argument BanqueDTO banqueDTO,@Argument String id){
        Banque banque=banqueRepository.findById(id).get();
        banque.setAbrege(banqueDTO.getAbrege()==null?banque.getAbrege():banqueDTO.getAbrege());
        banque.setIntitule(banqueDTO.getIntitule()==null?banque.getIntitule():banqueDTO.getIntitule());
        banque.setInterlocuteur(banqueDTO.getInterlocuteur()==null?banque.getInterlocuteur():banqueDTO.getInterlocuteur());
        banque.setCodeBIC(banqueDTO.getCodeBIC()==null?banque.getCodeBIC():banqueDTO.getCodeBIC());
        banque.setAdresse(banqueDTO.getAdresse()==null?banque.getAdresse():banqueDTO.getAdresse());
        banque.setCode_postale(banqueDTO.getCode_postale()==null?banque.getCode_postale():banqueDTO.getCode_postale());
        banque.setVille(banqueDTO.getVille()==null?banque.getVille():banqueDTO.getVille());
        banque.setPays(banqueDTO.getPays()==null?banque.getPays():banqueDTO.getPays());
        banque.setTelephone(banqueDTO.getTelephone()==null?banque.getTelephone():banqueDTO.getTelephone());
        banque.setTelecopie(banqueDTO.getTelecopie()==null?banque.getTelecopie():banqueDTO.getTelecopie());
        banque.setEmail(banqueDTO.getEmail()==null?banque.getEmail():banqueDTO.getEmail());
        banque.setSite_internet(banqueDTO.getSite_internet()==null?banque.getSite_internet():banqueDTO.getSite_internet());
        banque.setAgence(banqueDTO.getAgenceId()==null?banque.getAgence():agenceRepository.findById(banqueDTO.getAgenceId()).get());
        return banqueRepository.save(banque);
    }
    @MutationMapping
    public Banque supprimerBanque(@Argument String id){
        Banque banque=banqueRepository.findById(id).get();
        banqueRepository.delete(banque);
        return banque;
    }
}
