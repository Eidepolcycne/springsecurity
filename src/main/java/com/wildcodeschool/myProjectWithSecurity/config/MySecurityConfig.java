package com.wildcodeschool.myProjectWithSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authz)-> {
            try {
                authz
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/avengers/assemble").hasRole("CHAMPION")
                        .requestMatchers("/secret-bases").hasRole("ADMIN")
                        .anyRequest().authenticated()
                        .and().formLogin()
                        .and().httpBasic();

                } catch (Exception e) {
                    e.printStackTrace();
            }
        });
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User
                .withUsername("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User
                .withUsername("Nick")
                .password(encoder.encode("flerken"))
                .roles("ADMIN")
                .build();

        UserDetails champion = User
                .withUsername("Steve")
                .password(encoder.encode("motdepasse"))
                .roles("CHAMPION")
                .build();

        return new InMemoryUserDetailsManager(List.of(user, admin, champion));
    }
}
