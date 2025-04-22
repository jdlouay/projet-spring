package com.louay.animaux.service;

import java.util.List;
import com.louay.animaux.entities.Groupe;

public interface GroupeService {
    Groupe saveGroupe(Groupe g);
    Groupe updateGroupe(Groupe g);
    void deleteGroupe(Groupe g);
    void deleteGroupeById(Long id);
    Groupe getGroupe(Long id);
    List<Groupe> getAllGroupes();
} 