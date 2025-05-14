package com.louay.animaux.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.louay.animaux.entities.Groupe;

@RepositoryRestResource(path = "groupes")
@CrossOrigin("http://localhost:4200/")
public interface GroupeRepository extends JpaRepository<Groupe, Long> {
} 