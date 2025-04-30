package com.louay.animaux.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/webjars/**", "/css/**", "/js/**").permitAll()
                .requestMatchers("/showCreate", "/saveAnimal")
                .hasAnyAuthority("ADMIN", "AGENT")
                .requestMatchers("/modifierAnimal", "/updateAnimal", "/supprimerAnimal")
                .hasAuthority("ADMIN")
                .requestMatchers("/ListeAnimaux", "/")
                .hasAnyAuthority("ADMIN", "AGENT", "USER")
                .requestMatchers("/login", "/accessDenied").permitAll()
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .loginProcessingUrl("/login")
                .failureUrl("/login?error=true")
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll())
            .exceptionHandling(exception -> exception.accessDeniedPage("/accessDenied"));
        
        return http.build();
    }
}