package com.louay.animaux.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
                PasswordEncoder passwordEncoder = passwordEncoder();

                UserDetails admin = User
                                .withUsername("admin")
                                .password(passwordEncoder.encode("123"))
                                .authorities("ADMIN")
                                .build();

                UserDetails agent = User
                                .withUsername("agent")
                                .password(passwordEncoder.encode("123"))
                                .authorities("AGENT", "USER")
                                .build();

                UserDetails user = User
                                .withUsername("user")
                                .password(passwordEncoder.encode("123"))
                                .authorities("USER")
                                .build();

                return new InMemoryUserDetailsManager(admin, agent, user);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests((requests) -> requests
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

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}