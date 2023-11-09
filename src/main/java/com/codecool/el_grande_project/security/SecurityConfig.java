package com.codecool.el_grande_project.security;

import com.codecool.el_grande_project.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetailService userDetailService;
    private JwtAuthEntryPoint authEntryPoint;

    @Autowired
    public SecurityConfig(CustomUserDetailService userDetailService, JwtAuthEntryPoint authEntryPoint) {
        this.userDetailService = userDetailService;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/user/**").permitAll()
                .requestMatchers("/api/add-legend").permitAll()
                .requestMatchers("/places'").permitAll()
                .requestMatchers("/placesToAccept'").permitAll()
                .requestMatchers("/api/accept-legend/**").permitAll()
                .and()
                .httpBasic();
        http.addFilterBefore(myJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return  http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws  Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter myJwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
}
