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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeAnimal;

    @NotNull
    @Size(min = 3, max = 50)
    private String nomAnimal;

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

    public Animal(String nomAnimal, Double poidsAnimal, Date dateNaissance) {
        this.nomAnimal = nomAnimal;
        this.poidsAnimal = poidsAnimal;
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "Animal [codeAnimal=" + codeAnimal + ", nomAnimal=" + nomAnimal +
                ", poidsAnimal=" + poidsAnimal + ", dateNaissance=" + dateNaissance + "]";
    }
}