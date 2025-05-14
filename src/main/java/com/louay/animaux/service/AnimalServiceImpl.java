package com.louay.animaux.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.louay.animaux.entities.Animal;
import com.louay.animaux.entities.Groupe;
import com.louay.animaux.repos.AnimalRepository;
import com.louay.animaux.dto.AnimalDTO;

@Service
public class AnimalServiceImpl implements AnimalService {
    
    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GroupeService groupeService;

    @Override
    public AnimalDTO saveAnimal(Animal a) {
        System.out.println("Animal avant save: " + a);
        if (a.getGroupe() != null) {
            System.out.println("Groupe associé: " + a.getGroupe().getCodeGroupe());
        }
        return convertEntityToDto(animalRepository.save(a));
    }

    @Override
    public AnimalDTO updateAnimal(Animal a) {
        return convertEntityToDto(animalRepository.save(a));
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
    public AnimalDTO getAnimal(Long id) {
        return animalRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElseThrow(() -> new RuntimeException("Animal non trouvé avec l'id: " + id));
    }

    @Override
    public List<AnimalDTO> getAllAnimaux() {
        return animalRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Animal> getAllAnimauxParPage(int page, int size) {
        return animalRepository.findAll(PageRequest.of(page, size));
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
    public List<Animal> trierAnimauxEspecePoids() {
        return animalRepository.trierAnimauxEspecePoids();
    }

    @Override
    public List<Animal> findByNomAnimalContains(String nom) {
        return animalRepository.findByNomAnimalContains(nom);
    }

    @Override
    public Animal convertDtoToEntity(AnimalDTO animalDto) {
        Animal animal = new Animal();
        animal.setCodeAnimal(animalDto.getCodeAnimal());
        animal.setNomAnimal(animalDto.getNomAnimal());
        animal.setEspeceAnimal(animalDto.getEspeceAnimal());
        animal.setPoidsAnimal(animalDto.getPoidsAnimal());
        animal.setDateNaissance(animalDto.getDateNaissance());
        if (animalDto.getCodeGroupe() != null) {
            Groupe groupe = groupeService.getGroupe(animalDto.getCodeGroupe());
            System.out.println("Groupe trouvé pour codeGroupe=" + animalDto.getCodeGroupe() + " : " + (groupe != null ? groupe.getNomGroupe() : "Aucun"));
            animal.setGroupe(groupe);
        } else {
            animal.setGroupe(null);
        }
        return animal;
    }

    @Override
    public AnimalDTO convertEntityToDto(Animal animal) {
        AnimalDTO dto = new AnimalDTO();
        dto.setCodeAnimal(animal.getCodeAnimal());
        dto.setNomAnimal(animal.getNomAnimal());
        dto.setEspeceAnimal(animal.getEspeceAnimal());
        dto.setPoidsAnimal(animal.getPoidsAnimal());
        dto.setDateNaissance(animal.getDateNaissance());
        if (animal.getGroupe() != null) {
            dto.setCodeGroupe(animal.getGroupe().getCodeGroupe());
            dto.setNomGroupe(animal.getGroupe().getNomGroupe());
        } else {
            dto.setCodeGroupe(null);
            dto.setNomGroupe(null);
        }
        return dto;
    }
} 