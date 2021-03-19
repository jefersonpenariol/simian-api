package br.com.jeferson.simian.api.services;

import br.com.jeferson.simian.api.dtos.StatsDto;
import br.com.jeferson.simian.api.entities.Dna;

public interface DnaService {
	Boolean isSimian(String[] dna);
	
	StatsDto getStats();
	
	Dna addDna(Dna dna, Boolean isSimian);
}
