package br.com.jeferson.simian.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jeferson.simian.api.dtos.StatsDto;
import br.com.jeferson.simian.api.entities.Dna;
import br.com.jeferson.simian.api.repositories.DnaRepository;
import br.com.jeferson.simian.api.services.DnaService;


@Service
public class DnaServiceImpl implements DnaService{
	private static final String VALID_CHARS = "A|T|C|G";
	@Autowired
	DnaRepository repository;
	
	@Override
	public Boolean isSimian(String[] dna) {
		//TODO Lógica que permit verificar se DNA é simio ou não
		return false;
	}

	@Override
	public StatsDto getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dna addDna(Dna dna, Boolean isSimian) {
		return repository.save(null);
	}

}
