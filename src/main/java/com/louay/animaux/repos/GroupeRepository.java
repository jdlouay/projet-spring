package com.louay.animaux.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.louay.animaux.entities.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
} 