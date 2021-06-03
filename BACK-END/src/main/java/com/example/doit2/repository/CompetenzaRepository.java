package com.example.doit2.repository;

import com.example.doit2.model.Competenza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CompetenzaRepository extends CrudRepository<Competenza, Integer> {

    public Optional<Competenza> findByNome(String nome);

}
