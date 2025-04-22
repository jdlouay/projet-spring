package com.louay.animaux.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.louay.animaux.entities.Animal;
import com.louay.animaux.entities.Groupe;
import com.louay.animaux.repos.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService {
    
    @Autowired
    AnimalRepository animalRepository;

    @Override
    public Animal saveAnimal(Animal a) {
        return animalRepository.save(a);
    }

    @Override
    public Animal updateAnimal(Animal a) {
        return animalRepository.save(a);
    }

    @Override
    public void deleteAnimal(Animal a) {
        animalRepository.delete(a);
    }

    @Override
    public void deleteAnimalById(Long id) {
        animalRepository.deleteById(id);
    }

    @Override
    public Animal getAnimal(Long id) {
        return animalRepository.findById(id).get();
    }

    @Override
    public List<Animal> getAllAnimaux() {
        return animalRepository.findAll();
    }

    @Override
    public Page<Animal> getAllAnimauxParPage(int page, int size) {
        return animalRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Animal> findByEspeceAnimal(String espece) {
        return animalRepository.findByEspeceAnimal(espece);
    }

    @Override
    public List<Animal> findByEspeceAnimalContains(String espece) {
        return animalRepository.findByEspeceAnimalContains(espece);
    }

    @Override
    public List<Animal> findByEspecePoids(String espece, Double poids) {
        return animalRepository.findByEspecePoids(espece, poids);
    }

    @Override
    public List<Animal> findByGroupe(Groupe groupe) {
        return animalRepository.findByGroupe(groupe);
    }

    @Override
    public List<Animal> findByGroupeCodeGroupe(Long id) {
        return animalRepository.findByGroupeCodeGroupe(id);
    }

    @Override
    public List<Animal> findByOrderByEspeceAnimalAsc() {
        return animalRepository.findByOrderByEspeceAnimalAsc();
    }

    @Override
    public List<Animal> trierAnimauxEspecePoids() {
        return animalRepository.trierAnimauxEspecePoids();
    }
} 