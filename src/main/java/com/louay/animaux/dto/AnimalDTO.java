package com.louay.animaux.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalDTO {
    private Long codeAnimal;
    private String nomAnimal;
    private String especeAnimal;
    private Double poidsAnimal;
    private Date dateNaissance;
    private String nomGroupe;
} 