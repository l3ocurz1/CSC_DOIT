package com.example.doit2.repository;

import com.example.doit2.model.Esperto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  EspertoRepository extends CrudRepository<Esperto, Integer> {

    public Optional<Esperto> findByUserName(String username);

}

