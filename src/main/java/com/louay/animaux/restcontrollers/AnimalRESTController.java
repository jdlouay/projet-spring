package com.louay.animaux.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.louay.animaux.entities.Animal;
import com.louay.animaux.entities.Groupe;
import com.louay.animaux.service.AnimalService;
import com.louay.animaux.service.GroupeService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AnimalRESTController {
    @Autowired
    AnimalService animalService;
    
    @Autowired
    GroupeService groupeService;
    
    // Retourner tous les animaux
    @RequestMapping(method = RequestMethod.GET)
    public List<Animal> getAllAnimaux() {
        return animalService.getAllAnimaux();
    }
    
    // Retourner un animal par son ID
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Animal getAnimalById(@PathVariable("id") Long id) {
        return animalService.getAnimal(id);
    }
    
    // Créer un nouvel animal
    @RequestMapping(method = RequestMethod.POST)
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalService.saveAnimal(animal);
    }
    
    // Modifier un animal
    @RequestMapping(method = RequestMethod.PUT)
    public Animal updateAnimal(@RequestBody Animal animal) {
        return animalService.updateAnimal(animal);
    }
    
    // Supprimer un animal
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteAnimal(@PathVariable("id") Long id) {
        animalService.deleteAnimalById(id);
    }
    
    // Retourner les animaux d'un groupe
    @RequestMapping(value="/animauxgroupe/{idGroupe}", method = RequestMethod.GET)
    public List<Animal> getAnimauxByGroupeId(@PathVariable("idGroupe") Long idGroupe) {
        return animalService.findByGroupeCodeGroupe(idGroupe);
    }

    // Mettre à jour le groupe d'un animal
    @RequestMapping(value="/{animalId}/groupe/{groupeId}", method = RequestMethod.PUT)
    public Animal updateAnimalGroupe(
            @PathVariable("animalId") Long animalId,
            @PathVariable("groupeId") Long groupeId) {
        Animal animal = animalService.getAnimal(animalId);
        Groupe groupe = groupeService.getGroupe(groupeId);
        animal.setGroupe(groupe);
        return animalService.updateAnimal(animal);
    }
} 