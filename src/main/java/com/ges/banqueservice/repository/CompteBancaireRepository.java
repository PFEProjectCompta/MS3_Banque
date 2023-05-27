package com.ges.banqueservice.repository;

import com.ges.banqueservice.entities.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire,String > {
}
