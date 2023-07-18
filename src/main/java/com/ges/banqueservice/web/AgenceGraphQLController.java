package com.ges.banqueservice.web;

import com.ges.banqueservice.dto.AgenceDTO;
import com.ges.banqueservice.entities.Agence;
import com.ges.banqueservice.repository.AgenceRepository;
import jakarta.persistence.ManyToOne;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class AgenceGraphQLController {
    private AgenceRepository agenceRepository;

    public AgenceGraphQLController(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }
    @QueryMapping
    public List<Agence> agenceList(){
        return agenceRepository.findAll();
    }
    @QueryMapping
    public Agence agenceById(@Argument String id){
        return agenceRepository.findById(id).get();
    }
    @QueryMapping
    public List<Agence> agenceByIdSociete(@Argument String id){
        List<Agence> agences=agenceRepository.findAll();
        List<Agence> agenceListBySocieteId=new ArrayList<>();
        for(int i=0;i<agences.size();i++){
            if(agences.get(i).getSocieteId().equals(id)){
                agenceListBySocieteId.add(agences.get(i));
            }
        }
        return agenceListBySocieteId;
    }
    @MutationMapping
    public Agence ajouterAgence(@Argument AgenceDTO agenceDTO){
        Agence agence=Agence.builder()
                .id(UUID.randomUUID().toString())
                .nom(agenceDTO.getNom())
                .complement(agenceDTO.getComplement())
                .code_postale(agenceDTO.getCode_postale())
                .ville(agenceDTO.getVille())
                .pays(agenceDTO.getPays())
                .societeId(agenceDTO.getSocieteId())
                .build();
        return agenceRepository.save(agence);
    }
    @MutationMapping
    public Agence modifierAgence(@Argument AgenceDTO agenceDTO,@Argument String id){
        Agence agence=agenceRepository.findById(id).get();
        agence.setNom(agenceDTO.getNom()==null?agence.getNom():agenceDTO.getNom());
        agence.setComplement(agenceDTO.getComplement()==null?agence.getComplement():agenceDTO.getComplement());
        agence.setCode_postale(agenceDTO.getCode_postale()==null?agence.getCode_postale():agenceDTO.getCode_postale());
        agence.setVille(agenceDTO.getVille()==null?agence.getVille():agenceDTO.getVille());
        agence.setPays(agenceDTO.getPays()==null?agence.getPays():agenceDTO.getPays());
        agence.setSocieteId(agenceDTO.getPays()==null?agence.getSocieteId():agenceDTO.getSocieteId());
        return agenceRepository.save(agence);
    }
    @MutationMapping
    public Agence supprimerAgence(@Argument String id){
        Agence agence=agenceRepository.findById(id).get();
        agenceRepository.delete(agence);
        return agence;
    }
}
