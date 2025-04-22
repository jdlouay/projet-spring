package com.louay.animaux.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.louay.animaux.entities.Groupe;
import com.louay.animaux.repos.GroupeRepository;

@Service
public class GroupeServiceImpl implements GroupeService {
    
    @Autowired
    GroupeRepository groupeRepository;

    @Override
    public Groupe saveGroupe(Groupe g) {
        return groupeRepository.save(g);
    }

    @Override
    public Groupe updateGroupe(Groupe g) {
        return groupeRepository.save(g);
    }

    @Override
    public void deleteGroupe(Groupe g) {
        groupeRepository.delete(g);
    }

    @Override
    public void deleteGroupeById(Long id) {
        groupeRepository.deleteById(id);
    }

    @Override
    public Groupe getGroupe(Long id) {
        return groupeRepository.findById(id).get();
    }

    @Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }
} 