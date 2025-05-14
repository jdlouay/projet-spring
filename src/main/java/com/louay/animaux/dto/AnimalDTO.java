package com.louay.animaux.dto;

import java.util.Date;

public class AnimalDTO {
    private Long codeAnimal;
    private String nomAnimal;
    private String especeAnimal;
    private Double poidsAnimal;
    private Date dateNaissance;
    private Long codeGroupe;
    private String nomGroupe;

    public Long getCodeAnimal() { return codeAnimal; }
    public void setCodeAnimal(Long codeAnimal) { this.codeAnimal = codeAnimal; }

    public String getNomAnimal() { return nomAnimal; }
    public void setNomAnimal(String nomAnimal) { this.nomAnimal = nomAnimal; }

    public String getEspeceAnimal() { return especeAnimal; }
    public void setEspeceAnimal(String especeAnimal) { this.especeAnimal = especeAnimal; }

    public Double getPoidsAnimal() { return poidsAnimal; }
    public void setPoidsAnimal(Double poidsAnimal) { this.poidsAnimal = poidsAnimal; }

    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

    public Long getCodeGroupe() { return codeGroupe; }
    public void setCodeGroupe(Long codeGroupe) { this.codeGroupe = codeGroupe; }

    public String getNomGroupe() { return nomGroupe; }
    public void setNomGroupe(String nomGroupe) { this.nomGroupe = nomGroupe; }
} 