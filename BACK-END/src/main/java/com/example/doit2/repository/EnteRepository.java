package com.example.doit2.repository;

import com.example.doit2.model.Ente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnteRepository extends CrudRepository<Ente, Integer> {

    public Optional<Ente> findByUserName(String username);

}
