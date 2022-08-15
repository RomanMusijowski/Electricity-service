package com.example.demo.security.config;

import com.example.demo.security.Http401UnauthorizedEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static java.lang.String.format;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final Http401UnauthorizedEntryPoint authEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http.exceptionHandling().authenticationEntryPoint(authEntryPoint);

        http.authorizeRequests()
                .antMatchers("/",
                        "/actuator/**",
                        "/api/auth/login").permitAll()
                .antMatchers("/api/accounts/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/addresses/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/api/services").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/api/services/client").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().authenticated();
        return http.build();
    }
}
