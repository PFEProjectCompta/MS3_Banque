package com.ges.banqueservice.web;

import com.ges.banqueservice.dto.CompteBancaireDTO;
import com.ges.banqueservice.entities.CompteBancaire;
import com.ges.banqueservice.repository.BanqueRepository;
import com.ges.banqueservice.repository.CompteBancaireRepository;
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

    public CompteBancaireGraphQLController(CompteBancaireRepository compteBancaireRepository, BanqueRepository banqueRepository) {
        this.compteBancaireRepository = compteBancaireRepository;
        this.banqueRepository = banqueRepository;
    }
    @QueryMapping
    public List<CompteBancaire> compteBancaireList(){
        return compteBancaireRepository.findAll();
    }
    @QueryMapping
    public CompteBancaire compteBancaireById(@Argument String id){
        return compteBancaireRepository.findById(id).get();
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
        return compteBancaireRepository.save(compteBancaire);
    }
    @MutationMapping
    public CompteBancaire supprimerCompteBancaire(@Argument String id){
        CompteBancaire compteBancaire=compteBancaireRepository.findById(id).get();
        compteBancaireRepository.delete(compteBancaire);
        return compteBancaire;
    }
}
