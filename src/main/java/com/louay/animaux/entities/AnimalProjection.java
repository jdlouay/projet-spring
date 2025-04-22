package com.louay.animaux.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nomEsp", types = { Animal.class })
public interface AnimalProjection {
    public String getEspeceAnimal();
    public Double getPoidsAnimal();
} 