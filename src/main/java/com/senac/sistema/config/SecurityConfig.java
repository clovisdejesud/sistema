package com.senac.sistema.config;

import com.senac.sistema.config.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {
    
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;


    // Codificador de senha
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Service responsável por carregar o usuário
    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetailsService();
    }

    // Provedor de autenticação usando o UserDetailsService e codificador
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Configuração das permissões e tela de login
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .headers(headers -> headers
                .xssProtection(xss -> xss.disable()) //Remove o cabeçalho aqui

        )
            
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/img/**", "/login", "/error").permitAll()
          
        //Apenas Administrador
            .requestMatchers("/usuario/cadastro", "/usuario/gravar").hasRole("Administrador")

        // Permissão total para ADMIN
            .requestMatchers("/**").hasAnyRole("Administrador", "Usuario", "Visualizador")
                
        .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        )
    
        .exceptionHandling(exception -> exception
            .accessDeniedHandler(accessDeniedHandler)
        );

        return http.build();
    }
}
