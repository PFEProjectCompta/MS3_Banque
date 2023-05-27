package com.ges.banqueservice.config;

import com.ges.banqueservice.entities.Agence;
import com.ges.banqueservice.entities.Banque;
import com.ges.banqueservice.entities.CompteBancaire;
import com.ges.banqueservice.entities.Contact;
import com.ges.banqueservice.repository.AgenceRepository;
import com.ges.banqueservice.repository.BanqueRepository;
import com.ges.banqueservice.repository.CompteBancaireRepository;
import com.ges.banqueservice.repository.ContactRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class InitialData {
    private static AgenceRepository agenceRepository;
    private static BanqueRepository banqueRepository;
    private static CompteBancaireRepository compteBancaireRepository;
    private static ContactRepository contactRepository;

    public InitialData(AgenceRepository agenceRepository, BanqueRepository banqueRepository, CompteBancaireRepository compteBancaireRepository, ContactRepository contactRepository) {
        this.agenceRepository = agenceRepository;
        this.banqueRepository = banqueRepository;
        this.compteBancaireRepository = compteBancaireRepository;
        this.contactRepository = contactRepository;
    }
    public static void ajouterAgence(){
        for(int i=0;i<10;i++){
            Agence agence=Agence.builder()
                    .id(UUID.randomUUID().toString())
                    .nom("Wiam")
                    .code_postale("1234")
                    .complement("L'agence de marrakech")
                    .pays("Maroc")
                    .ville("Tanger")
                    .build();
            System.out.println(agenceRepository.save(agence));
        }
    }
    public static void ajouterBanque(){
        List<Agence> agences=agenceRepository.findAll();
        agences.forEach(agence -> {
            for(int i=0;i<5;i++){
                Banque banque=Banque.builder()
                        .id(UUID.randomUUID().toString())
                        .abrege("abrger"+i)
                        .intitule("intet"+i)
                        .interlocuteur("Intelectuel"+i)
                        .codeBIC("ABCD")
                        .adresse(String.format("batiment %s immo %s",i,i*i))
                        .code_postale("code"+i)
                        .ville("ville"+i)
                        .pays("pays"+i)
                        .telecopie("telecopie"+i)
                        .telephone("telephone"+i)
                        .email(String.format("email%s@gmail.com",Math.random()*i))
                        .site_internet("site"+1*Math.random())
                        .agence(agence)
                        .build();
                System.out.println(banqueRepository.save(banque).getId());
            }
        });
    }
    public static void ajouterCompteBancaire(){
        List<Banque> banques=banqueRepository.findAll();
        banques.forEach(banque -> {
            for (int i=0;i<5;i++){
                CompteBancaire compteBancaire=CompteBancaire.builder()
                        .id(UUID.randomUUID().toString())
                        .abrege("abrege"+i)
                        .devise("devise"+i)
                        .pays("pays"+i)
                        .structure("structer"+i)
                        .num_compte("compte"+i)
                        .num_guichet("num"+i)
                        .banque(banque)
                        .build();
                System.out.println(compteBancaireRepository.save(compteBancaire).getId());
            }
        });
    }
    public static void ajoutercontact(){
        List<Banque> banques = banqueRepository.findAll();
        banques.forEach(banque -> {
            for(int i=0;i<5;i++){
                Contact contact= Contact.builder()
                        .id(UUID.randomUUID().toString())
                        .type_contact("type"+i)
                        .civilite("F")
                        .nom("wiam"+i)
                        .prenom("laqssir")
                        .service("service "+i)
                        .fonction("fonction "+i)
                        .telecopie("telecopie" +i)
                        .telephone("telephone "+i)
                        .portable("0949388281")
                        .email("email"+i+"@gmail.com")
                        .banque(banque)
                        .build();
                System.out.println(contactRepository.save(contact).getId());
            }
        });
    }
}
