package com.louay.animaux.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.louay.animaux.entities.Animal;
import com.louay.animaux.entities.Groupe;
import com.louay.animaux.dto.AnimalDTO;

public interface AnimalService {
    AnimalDTO saveAnimal(Animal a);
    AnimalDTO updateAnimal(Animal a);
    void deleteAnimal(Animal a);
    void deleteAnimalById(Long id);
    AnimalDTO getAnimal(Long id);
    List<AnimalDTO> getAllAnimaux();
    Page<Animal> getAllAnimauxParPage(int page, int size);
    
    List<Animal> findByEspecePoids(String espece, Double poids);
    List<Animal> findByGroupe(Groupe groupe);
    List<Animal> findByGroupeCodeGroupe(Long id);
    List<Animal> trierAnimauxEspecePoids();
    List<Animal> findByNomAnimalContains(String nom);

    // Méthodes de conversion
    AnimalDTO convertEntityToDto(Animal animal);
    Animal convertDtoToEntity(AnimalDTO animalDto);
} 