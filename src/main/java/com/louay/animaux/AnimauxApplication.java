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
        repositoryRestConfiguration.exposeIdsFor(Animal.class);
    }
    
    @PostConstruct
    void init_users() {
        // Vérifier si les rôles existent déjà
        if (userService.findUserByUsername("admin") == null) {
            // Ajouter les rôles
            Role adminRole = new Role();
            adminRole.setRole("ADMIN");
            userService.addRole(adminRole);

            Role agentRole = new Role();
            agentRole.setRole("AGENT");
            userService.addRole(agentRole);

            Role userRole = new Role();
            userRole.setRole("USER");
            userService.addRole(userRole);
            
            // Ajouter les utilisateurs
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("123");
            admin.setEnabled(true);
            admin.setRoles(new ArrayList<>());
            userService.saveUser(admin);

            User agent = new User();
            agent.setUsername("agent");
            agent.setPassword("123");
            agent.setEnabled(true);
            agent.setRoles(new ArrayList<>());
            userService.saveUser(agent);

            User user = new User();
            user.setUsername("user");
            user.setPassword("123");
            user.setEnabled(true);
            user.setRoles(new ArrayList<>());
            userService.saveUser(user);
            
            // Ajouter les rôles aux utilisateurs
            userService.addRoleToUser("admin", "ADMIN");
            userService.addRoleToUser("agent", "AGENT");
            userService.addRoleToUser("agent", "USER");
            userService.addRoleToUser("user", "USER");
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}