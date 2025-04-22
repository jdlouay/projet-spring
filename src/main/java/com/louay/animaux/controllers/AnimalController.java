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

import jakarta.validation.Valid;

import com.louay.animaux.entities.Animal;
import com.louay.animaux.entities.Groupe;
import com.louay.animaux.service.AnimalService;
import com.louay.animaux.service.GroupeService;

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
        List<Groupe> groupes = groupeService.getAllGroupes();
        modelMap.addAttribute("animal", new Animal());
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("groupes", groupes);
        return "formAnimal";
    }

    @RequestMapping("/saveAnimal")
    public String saveAnimal(@Valid @ModelAttribute("animal") Animal animal,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        if (bindingResult.hasErrors())
            return "formAnimal";

        boolean isNew = (animal.getCodeAnimal() == null);
        animalService.saveAnimal(animal);

        if (isNew) {
            Page<Animal> animaux = animalService.getAllAnimauxParPage(page, size);
            page = animaux.getTotalPages() - 1;
        }
        return "redirect:/ListeAnimaux?page=" + page + "&size=" + size;
    }

    @RequestMapping("/ListeAnimaux")
    public String listeAnimaux(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<Animal> animaux = animalService.getAllAnimauxParPage(page, size);
        modelMap.addAttribute("animaux", animaux);
        modelMap.addAttribute("pages", new int[animaux.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeAnimaux";
    }

    @RequestMapping("/supprimerAnimal")
    public String supprimerAnimal(@RequestParam("id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap) {
        animalService.deleteAnimalById(id);
        Page<Animal> animaux = animalService.getAllAnimauxParPage(page, size);
        modelMap.addAttribute("animaux", animaux);
        modelMap.addAttribute("pages", new int[animaux.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeAnimaux";
    }

    @RequestMapping("/modifierAnimal")
    public String editerAnimal(@RequestParam("id") Long id, ModelMap modelMap) {
        Animal animal = animalService.getAnimal(id);
        List<Groupe> groupes = groupeService.getAllGroupes();
        modelMap.addAttribute("animal", animal);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("groupes", groupes);
        return "formAnimal";
    }

    @RequestMapping("/updateAnimal")
    public String updateAnimal(@ModelAttribute("animal") Animal animal,
            @RequestParam("date") String date,
            ModelMap modelMap) throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        dateformat.setLenient(false);
        Date dateNaissance = dateformat.parse(String.valueOf(date));
        animal.setDateNaissance(dateNaissance);

        animalService.updateAnimal(animal);
        return "redirect:/ListeAnimaux";
    }
}