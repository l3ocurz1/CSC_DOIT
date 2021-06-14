package com.example.doit2.repository;

import com.example.doit2.model.Progettista;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgettistaRepository extends CrudRepository<Progettista, Integer> {
    
    Optional<Progettista> findByUserName(String username);

}
