package com.louay.animaux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.modelmapper.ModelMapper;

import com.louay.animaux.entities.Animal;
import com.louay.animaux.entities.Groupe;
import com.louay.animaux.entities.Role;
import com.louay.animaux.entities.User;
import com.louay.animaux.service.UserService;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;

@SpringBootApplication
public class AnimauxApplication implements CommandLineRunner {

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
    
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(AnimauxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Animal.class, Groupe.class);
    }
    
    @PostConstruct
    void init_users() {
        // ajouter les rôles
        userService.addRole(new Role(null, "ADMIN"));
        userService.addRole(new Role(null, "USER"));
        // ajouter les users UNIQUEMENT s'ils n'existent pas déjà
        if (userService.findUserByUsername("admin") == null) {
            userService.saveUser(new User(null, "admin", "123", true, new ArrayList<>()));
            userService.addRoleToUser("admin", "ADMIN");
            userService.addRoleToUser("admin", "USER");
        }
        if (userService.findUserByUsername("nadhem") == null) {
            userService.saveUser(new User(null, "nadhem", "123", true, new ArrayList<>()));
            userService.addRoleToUser("nadhem", "USER");
        }
        if (userService.findUserByUsername("yassine") == null) {
            userService.saveUser(new User(null, "yassine", "123", true, new ArrayList<>()));
            userService.addRoleToUser("yassine", "USER");
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}