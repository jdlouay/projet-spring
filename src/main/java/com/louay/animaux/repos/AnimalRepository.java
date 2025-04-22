package com.louay.animaux.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.louay.animaux.entities.Animal;
import com.louay.animaux.entities.Groupe;

import java.util.List;

@RepositoryRestResource(path = "rest")
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByNomAnimal(String nom);

    List<Animal> findByNomAnimalContains(String nom);

    @Query("select a from Animal a where a.nomAnimal like %:nom and a.poidsAnimal > :poids")
    List<Animal> findByEspecePoids(@Param("nom") String nom, @Param("poids") Double poids);

    @Query("select a from Animal a where a.groupe = ?1")
    List<Animal> findByGroupe(Groupe groupe);

    List<Animal> findByGroupeCodeGroupe(Long id);

    List<Animal> findByOrderByNomAnimalAsc();

    @Query("select a from Animal a order by a.nomAnimal ASC, a.poidsAnimal DESC")
    List<Animal> trierAnimauxEspecePoids();
}