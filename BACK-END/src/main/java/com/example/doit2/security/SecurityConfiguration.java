package com.example.doit2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/guest/**", "/h2-console/**").permitAll()
                    .antMatchers("/progettista/listaCompetenze").permitAll()
                    .antMatchers("/progettista/**").hasAnyAuthority("PROPONENTE", "PROGETTISTA", "ENTE", "ESPERTO")
                    .antMatchers(
                            "/proponente/aggiungiRequisito",
                            "/proponente/removeRequisito",
                            "/proponente/getAltriRequisiti",
                            "/proponente/setProgettoChiuso",
                            "/proponente/setProgettoCompletato",
                            "/proponente/approvaProgettista",
                            "/proponente/rifiutaProgettista",
                            "/proponente/espelliProgettista")
                            .hasAnyAuthority("PROPONENTE", "ESPERTO")
                    .antMatchers("/proponente/**").hasAnyAuthority("PROPONENTE", "ENTE")
                    .antMatchers("/ente/**").hasAuthority("ENTE")
                    .antMatchers("/esperto/**").hasAuthority("ESPERTO")
                    .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
