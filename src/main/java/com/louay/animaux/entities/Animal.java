package com.louay.animaux.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeAnimal;

    @NotNull
    @Size(min = 3, max = 50)
    private String nomAnimal;

    @NotNull
    @Size(min = 3, max = 50)
    private String especeAnimal;

    @NotNull
    @Min(value = 0)
    @Max(value = 10000)
    private Double poidsAnimal;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateNaissance;

    @ManyToOne
    private Groupe groupe;

    public Animal() {
    }

    public Animal(String nomAnimal, String especeAnimal, Double poidsAnimal, Date dateNaissance) {
        this.nomAnimal = nomAnimal;
        this.especeAnimal = especeAnimal;
        this.poidsAnimal = poidsAnimal;
        this.dateNaissance = dateNaissance;
    }

    public Long getCodeAnimal() {
        return codeAnimal;
    }

    public void setCodeAnimal(Long codeAnimal) {
        this.codeAnimal = codeAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public String getEspeceAnimal() {
        return especeAnimal;
    }

    public void setEspeceAnimal(String especeAnimal) {
        this.especeAnimal = especeAnimal;
    }

    public Double getPoidsAnimal() {
        return poidsAnimal;
    }

    public void setPoidsAnimal(Double poidsAnimal) {
        this.poidsAnimal = poidsAnimal;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    @Override
    public String toString() {
        return "Animal [codeAnimal=" + codeAnimal + ", nomAnimal=" + nomAnimal +
                ", especeAnimal=" + especeAnimal + ", poidsAnimal=" + poidsAnimal + 
                ", dateNaissance=" + dateNaissance + "]";
    }
}