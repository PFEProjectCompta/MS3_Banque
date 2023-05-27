package com.ges.banqueservice;

import com.ges.banqueservice.config.InitialData;
import com.ges.banqueservice.entities.Agence;
import com.ges.banqueservice.entities.Banque;
import com.ges.banqueservice.entities.CompteBancaire;
import com.ges.banqueservice.entities.Contact;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BanqueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanqueServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(){
		return args -> {
			InitialData.ajouterAgence();
			InitialData.ajouterBanque();
			InitialData.ajouterCompteBancaire();
			InitialData.ajoutercontact();
		};
	};

}
