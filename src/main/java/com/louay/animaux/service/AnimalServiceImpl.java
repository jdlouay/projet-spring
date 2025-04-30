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

    @Override
    public AnimalDTO saveAnimal(Animal a) {
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

    @Override
    public AnimalDTO convertEntityToDto(Animal animal) {
        AnimalDTO animalDTO = modelMapper.map(animal, AnimalDTO.class);
        if (animal.getGroupe() != null) {
            animalDTO.setNomGroupe(animal.getGroupe().getNomGroupe());
        }
        return animalDTO;
    }

    @Override
    public Animal convertDtoToEntity(AnimalDTO animalDto) {
        return modelMapper.map(animalDto, Animal.class);
    }
} 