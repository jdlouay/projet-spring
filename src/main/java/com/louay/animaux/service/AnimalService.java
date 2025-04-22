package com.louay.animaux.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.louay.animaux.entities.Animal;
import com.louay.animaux.entities.Groupe;

public interface AnimalService {
    Animal saveAnimal(Animal a);
    Animal updateAnimal(Animal a);
    void deleteAnimal(Animal a);
    void deleteAnimalById(Long id);
    Animal getAnimal(Long id);
    List<Animal> getAllAnimaux();
    Page<Animal> getAllAnimauxParPage(int page, int size);
    
    List<Animal> findByEspeceAnimal(String espece);
    List<Animal> findByEspeceAnimalContains(String espece);
    List<Animal> findByEspecePoids(String espece, Double poids);
    List<Animal> findByGroupe(Groupe groupe);
    List<Animal> findByGroupeCodeGroupe(Long id);
    List<Animal> findByOrderByEspeceAnimalAsc();
    List<Animal> trierAnimauxEspecePoids();
} 