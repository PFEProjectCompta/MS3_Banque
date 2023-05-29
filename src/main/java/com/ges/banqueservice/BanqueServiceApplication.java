package com.ges.banqueservice;

import com.ges.banqueservice.config.InitialData;
import com.ges.banqueservice.model.PlanComptableElement;
import com.ges.banqueservice.service.PlanComptableRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class BanqueServiceApplication {
	private PlanComptableRestClientService planComptableRestClientService;

	public BanqueServiceApplication(PlanComptableRestClientService planComptableRestClientService) {
		this.planComptableRestClientService = planComptableRestClientService;
	}

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
