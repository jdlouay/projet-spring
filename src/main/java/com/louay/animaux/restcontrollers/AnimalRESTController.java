package com.louay.animaux.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.louay.animaux.dto.AnimalDTO;
import com.louay.animaux.entities.Animal;
import com.louay.animaux.entities.Groupe;
import com.louay.animaux.service.AnimalService;
import com.louay.animaux.service.GroupeService;

@RestController
@RequestMapping("/animaux/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AnimalRESTController {
    @Autowired
    AnimalService animalService;
    
    @Autowired
    GroupeService groupeService;
    
    // Retourner tous les animaux
    @RequestMapping(method = RequestMethod.GET)
    public List<AnimalDTO> getAllAnimaux() {
        return animalService.getAllAnimaux();
    }
    
    // Retourner un animal par son ID
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public AnimalDTO getAnimalById(@PathVariable("id") Long id) {
        return animalService.getAnimal(id);
    }
    
    // Créer un nouvel animal
    @RequestMapping(method = RequestMethod.POST)
    public AnimalDTO createAnimal(@RequestBody AnimalDTO animalDTO) {
        Animal animal = animalService.convertDtoToEntity(animalDTO);
        return animalService.saveAnimal(animal);
    }
    
    // Modifier un animal
    @RequestMapping(method = RequestMethod.PUT)
    public AnimalDTO updateAnimal(@RequestBody AnimalDTO animalDTO) {
        Animal animal = animalService.convertDtoToEntity(animalDTO);
        return animalService.updateAnimal(animal);
    }
    
    // Supprimer un animal
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteAnimal(@PathVariable("id") Long id) {
        animalService.deleteAnimalById(id);
    }
    
    // Retourner les animaux d'un groupe
    @RequestMapping(value="/groupes/{idGroupe}", method = RequestMethod.GET)
    public List<AnimalDTO> getAnimauxByGroupeId(@PathVariable("idGroupe") Long idGroupe) {
        List<Animal> animaux = animalService.findByGroupeCodeGroupe(idGroupe);
        return animaux.stream()
                .map(animalService::convertEntityToDto)
                .collect(java.util.stream.Collectors.toList());
    }

    // Mettre à jour le groupe d'un animal
    @RequestMapping(value="/{animalId}/groupe/{groupeId}", method = RequestMethod.PUT)
    public AnimalDTO updateAnimalGroupe(
            @PathVariable("animalId") Long animalId,
            @PathVariable("groupeId") Long groupeId) {
        Animal animal = animalService.convertDtoToEntity(animalService.getAnimal(animalId));
        Groupe groupe = groupeService.getGroupe(groupeId);
        animal.setGroupe(groupe);
        return animalService.updateAnimal(animal);
    }

    // Rechercher les animaux par nom
    @RequestMapping(value="/nom/{nom}", method = RequestMethod.GET)
    public List<AnimalDTO> findByNomAnimalContains(@PathVariable("nom") String nom) {
        List<Animal> animaux = animalService.findByNomAnimalContains(nom);
        return animaux.stream()
                .map(animalService::convertEntityToDto)
                .collect(java.util.stream.Collectors.toList());
    }
} 