package com.louay.animaux.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.louay.animaux.entities.Groupe;
import com.louay.animaux.service.GroupeService;

@RestController
@RequestMapping("/api/groupes")
@CrossOrigin
public class GroupeRESTController {
    @Autowired
    GroupeService groupeService;
    
    // Retourner tous les groupes
    @RequestMapping(method = RequestMethod.GET)
    public List<Groupe> getAllGroupes() {
        return groupeService.getAllGroupes();
    }
    
    // Retourner un groupe par son ID
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Groupe getGroupeById(@PathVariable("id") Long id) {
        return groupeService.getGroupe(id);
    }
    
    // Créer un nouveau groupe
    @RequestMapping(method = RequestMethod.POST)
    public Groupe createGroupe(@RequestBody Groupe groupe) {
        return groupeService.saveGroupe(groupe);
    }
    
    // Modifier un groupe
    @RequestMapping(method = RequestMethod.PUT)
    public Groupe updateGroupe(@RequestBody Groupe groupe) {
        return groupeService.updateGroupe(groupe);
    }
    
    // Supprimer un groupe
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteGroupe(@PathVariable("id") Long id) {
        groupeService.deleteGroupeById(id);
    }
} 