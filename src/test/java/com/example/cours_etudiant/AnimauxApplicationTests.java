package com.example.cours_etudiant;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cours_etudiant.entities.Animal;
import com.example.cours_etudiant.repos.AnimalRepository;

@SpringBootTest
class AnimauxApplicationTests {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void testCreateAnimal() {
        Animal animal = new Animal("Chien", 25.5, new Date());
        animalRepository.save(animal);
    }

    @Test
    public void testFindAnimal() {
        Animal a = animalRepository.findById(1L).get();
        System.out.println(a);
    }

    @Test
    public void testUpdateAnimal() {
        Animal a = animalRepository.findById(1L).get();
        a.setPoidsAnimal(30.0);
        animalRepository.save(a);
    }

    @Test
    public void testDeleteAnimal() {
        animalRepository.deleteById(1L);
    }

    @Test
    public void testListerTousAnimaux() {
        List<Animal> animaux = animalRepository.findAll();
        for (Animal a : animaux) {
            System.out.println(a);
        }
    }
} 