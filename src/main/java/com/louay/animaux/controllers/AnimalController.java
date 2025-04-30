package com.louay.animaux.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import com.louay.animaux.entities.Animal;
import com.louay.animaux.entities.Groupe;
import com.louay.animaux.service.AnimalService;
import com.louay.animaux.service.GroupeService;
import com.louay.animaux.dto.AnimalDTO;

@Controller
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Autowired
    GroupeService groupeService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/ListeAnimaux";
    }

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        try {
            Animal animal = new Animal();
            animal.setDateNaissance(new Date()); // Date par défaut
            animal.setEspeceAnimal(""); // Initialiser l'espèce
            List<Groupe> groupes = groupeService.getAllGroupes();
            
            modelMap.addAttribute("animal", animal);
            modelMap.addAttribute("mode", "new");
            modelMap.addAttribute("groupes", groupes);
            return "formAnimal";
        } catch (Exception e) {
            modelMap.addAttribute("error", "Erreur lors de l'initialisation du formulaire : " + e.getMessage());
            return "error";
        }
    }

    @RequestMapping("/saveAnimal")
    public String saveAnimal(@Valid @ModelAttribute("animal") Animal animal,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Début saveAnimal - Animal reçu: " + animal);
            System.out.println("Nom: " + animal.getNomAnimal());
            System.out.println("Poids: " + animal.getPoidsAnimal());
            System.out.println("Date: " + animal.getDateNaissance());
            System.out.println("Groupe: " + animal.getGroupe());

            if (bindingResult.hasErrors()) {
                System.out.println("Erreurs de validation trouvées:");
                bindingResult.getAllErrors().forEach(error -> {
                    System.out.println(error.getDefaultMessage());
                });
                List<Groupe> groupes = groupeService.getAllGroupes();
                modelMap.addAttribute("groupes", groupes);
                modelMap.addAttribute("mode", "new");
                return "formAnimal";
            }

            if (animal.getNomAnimal() == null || animal.getNomAnimal().trim().isEmpty()) {
                System.out.println("Erreur: Nom d'animal vide");
                redirectAttributes.addFlashAttribute("error", "Le nom de l'animal ne peut pas être vide");
                return "redirect:/showCreate";
            }

            System.out.println("Avant sauvegarde - Animal: " + animal);
            boolean isNew = (animal.getCodeAnimal() == null);
            animalService.saveAnimal(animal);
            System.out.println("Après sauvegarde - Animal: " + animal);

            if (isNew) {
                Page<Animal> animaux = animalService.getAllAnimauxParPage(page, size);
                page = animaux.getTotalPages() - 1;
            }
            
            redirectAttributes.addFlashAttribute("success", "Animal enregistré avec succès");
            return "redirect:/ListeAnimaux?page=" + page + "&size=" + size;
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'enregistrement de l'animal: " + e.getMessage());
            return "redirect:/showCreate";
        }
    }

    @RequestMapping("/ListeAnimaux")
    public String listeAnimaux(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        try {
            Page<Animal> animaux = animalService.getAllAnimauxParPage(page, size);
            modelMap.addAttribute("animaux", animaux);
            modelMap.addAttribute("pages", new int[animaux.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("size", size);
            return "listeAnimaux";
        } catch (Exception e) {
            modelMap.addAttribute("error", "Erreur lors de la récupération des animaux: " + e.getMessage());
            return "error";
        }
    }

    @RequestMapping("/supprimerAnimal")
    public String supprimerAnimal(@RequestParam("id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {
        try {
            animalService.deleteAnimalById(id);
            redirectAttributes.addFlashAttribute("success", "Animal supprimé avec succès");
            return "redirect:/ListeAnimaux?page=" + page + "&size=" + size;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de l'animal: " + e.getMessage());
            return "redirect:/ListeAnimaux?page=" + page + "&size=" + size;
        }
    }

    @RequestMapping("/modifierAnimal")
    public String editerAnimal(@RequestParam("id") Long id, 
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {
        try {
            AnimalDTO animalDTO = animalService.getAnimal(id);
            Animal animal = animalService.convertDtoToEntity(animalDTO);
            List<Groupe> groupes = groupeService.getAllGroupes();
            modelMap.addAttribute("animal", animal);
            modelMap.addAttribute("mode", "edit");
            modelMap.addAttribute("groupes", groupes);
            modelMap.addAttribute("page", page);
            modelMap.addAttribute("size", size);
            return "formAnimal";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la récupération de l'animal: " + e.getMessage());
            return "redirect:/ListeAnimaux?page=" + page + "&size=" + size;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Une erreur inattendue s'est produite: " + e.getMessage());
            return "redirect:/ListeAnimaux?page=" + page + "&size=" + size;
        }
    }

    @RequestMapping("/updateAnimal")
    public String updateAnimal(@ModelAttribute("animal") Animal animal,
            @RequestParam("date") String date,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            dateformat.setLenient(false);
            Date dateNaissance = dateformat.parse(String.valueOf(date));
            animal.setDateNaissance(dateNaissance);

            animalService.updateAnimal(animal);
            redirectAttributes.addFlashAttribute("success", "Animal modifié avec succès");
            return "redirect:/ListeAnimaux";
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("error", "Format de date invalide");
            return "redirect:/modifierAnimal?id=" + animal.getCodeAnimal();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification de l'animal: " + e.getMessage());
            return "redirect:/modifierAnimal?id=" + animal.getCodeAnimal();
        }
    }
}