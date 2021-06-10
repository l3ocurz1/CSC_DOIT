package com.example.doit2.repository;

import com.example.doit2.model.Proponente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProponenteRepository extends CrudRepository<Proponente, Integer> {

    public Optional<Proponente> findByUserName(String username);

}
