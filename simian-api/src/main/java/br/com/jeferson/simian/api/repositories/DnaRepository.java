package br.com.jeferson.simian.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jeferson.simian.api.entities.Dna;

public interface DnaRepository extends JpaRepository<Dna, String[]>{

}
