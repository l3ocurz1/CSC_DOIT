package com.example.doit2.security;

import com.example.doit2.model.Progettista;
import com.example.doit2.repository.ProgettistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ProgettistaRepository progettistaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Progettista progettista = progettistaRepository.findByUserName(username).get();
        return new org.springframework.security.core.userdetails.User(progettista.getUserName(), progettista.getPassword(), progettista.getAuthorities());
    }

}
