package dev.adoria.gamevault.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/users").permitAll()
            .requestMatchers(HttpMethod.GET, "/csrf").permitAll()
            .anyRequest().authenticated());
        http.formLogin(formConfig -> formConfig
            .successHandler((request, response, authentication)
                -> response.setStatus(HttpStatus.OK.value()))
            .failureHandler((request, response, exception)
                -> response.setStatus(HttpStatus.UNAUTHORIZED.value())
            )
        );
        http.exceptionHandling(exceptionHandlingConfig -> exceptionHandlingConfig
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
        http.logout(logoutConfig -> logoutConfig
            .logoutSuccessHandler((request, response, authentication)
                -> response.setStatus(HttpStatus.NO_CONTENT.value())));
        return http.build();
    }
}