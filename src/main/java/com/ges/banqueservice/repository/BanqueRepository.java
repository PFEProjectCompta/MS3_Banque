package com.ges.banqueservice.repository;

import com.ges.banqueservice.entities.Banque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BanqueRepository extends JpaRepository<Banque,String> {
}
