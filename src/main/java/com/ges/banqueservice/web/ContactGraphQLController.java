package com.ges.banqueservice.web;

import com.ges.banqueservice.dto.ContactDTO;
import com.ges.banqueservice.entities.Contact;
import com.ges.banqueservice.repository.BanqueRepository;
import com.ges.banqueservice.repository.ContactRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ContactGraphQLController {
    private ContactRepository contactRepository;
    private BanqueRepository banqueRepository;

    public ContactGraphQLController(ContactRepository contactRepository, BanqueRepository banqueRepository) {
        this.contactRepository = contactRepository;
        this.banqueRepository = banqueRepository;
    }
    @QueryMapping
    public List<Contact>  contactList(){
        return contactRepository.findAll();
    }
    @QueryMapping
    public Contact contactById(@Argument String id){
        return contactRepository.findById(id).get();
    }
    @MutationMapping
    public Contact ajouterContact(@Argument ContactDTO contactDTO){
        Contact contact=Contact.builder()
                .id(UUID.randomUUID().toString())
                .type_contact(contactDTO.getType_contact())
                .civilite(contactDTO.getCivilite())
                .nom(contactDTO.getNom())
                .prenom(contactDTO.getPrenom())
                .service(contactDTO.getService())
                .fonction(contactDTO.getFonction())
                .telephone(contactDTO.getTelephone())
                .portable(contactDTO.getPortable())
                .email(contactDTO.getEmail())
                .telecopie(contactDTO.getTelecopie())
                .banque(banqueRepository.findById(contactDTO.getBanqueId()).get())
                .build();
        return contactRepository.save(contact);
    }
    @MutationMapping
    public Contact modifierContact(@Argument ContactDTO contactDTO,@Argument String id){
        Contact contact=contactRepository.findById(id).get();
        contact.setType_contact(contactDTO.getType_contact()==null?contact.getType_contact():contactDTO.getType_contact());
        contact.setCivilite(contactDTO.getCivilite()==null?contact.getCivilite():contactDTO.getCivilite());
        contact.setNom(contactDTO.getNom()==null?contact.getNom():contactDTO.getNom());
        contact.setPrenom(contactDTO.getPrenom()==null?contact.getPrenom():contactDTO.getPrenom());
        contact.setService(contactDTO.getService()==null?contact.getService():contactDTO.getService());
        contact.setFonction(contactDTO.getFonction()==null?contact.getFonction():contactDTO.getFonction());
        contact.setTelecopie(contactDTO.getTelecopie()==null?contact.getTelecopie():contactDTO.getTelecopie());
        contact.setTelephone(contactDTO.getTelephone()==null?contact.getTelephone():contactDTO.getTelephone());
        contact.setPortable(contactDTO.getPortable()==null?contact.getPortable():contactDTO.getPortable());
        contact.setEmail(contactDTO.getEmail()==null?contact.getEmail():contactDTO.getEmail());
        contact.setBanque(contactDTO.getBanqueId()==null?contact.getBanque():banqueRepository.findById(contactDTO.getBanqueId()).get());
        return contactRepository.save(contact);
    }
    @MutationMapping
    public Contact supprimerContact(@Argument String id){
        Contact contact=contactRepository.findById(id).get();
        contactRepository.delete(contact);
        return contact;
    }
}
