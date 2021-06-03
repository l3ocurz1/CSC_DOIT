package com.example.doit2.repository;

import com.example.doit2.model.Progetto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgettoRepository extends CrudRepository<Progetto, Integer> {

    Optional<Progetto> findByDescrizione(String descrizione);

}
