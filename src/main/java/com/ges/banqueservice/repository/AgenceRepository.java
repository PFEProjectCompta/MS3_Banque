package com.ges.banqueservice.repository;

import com.ges.banqueservice.entities.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AgenceRepository extends JpaRepository<Agence,String > {
}
