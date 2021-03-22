package br.com.jeferson.simian.api.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.jeferson.simian.api.dtos.StatsDto;
import br.com.jeferson.simian.api.entities.Dna;
import br.com.jeferson.simian.api.enums.DnaTypeEnum;
import br.com.jeferson.simian.api.repositories.DnaRepository;
import br.com.jeferson.simian.api.services.DnaService;
import br.com.jeferson.simian.api.util.SimianUtil;


@Service
public class DnaServiceImpl implements DnaService{
	private static final String VALID_CHARS = "A|T|C|G";
	private static final int VALID_SEQUENCE = 4;
	
	@Autowired
	DnaRepository repository;
	
	@Override
	public Boolean isSimian(String[] dna) {
		List<Object> allDna = new ArrayList<Object>();
		Boolean isSimian = false;
		int posicao = 0;
		Object[] vertSeq = SimianUtil.transformToHorizon(dna);
		
		allDna.addAll(Arrays.asList(dna));
		allDna.addAll(Arrays.asList(vertSeq));
		for(Object sequence : dna) { 
			String letra = "";
			String seqH = "";
			
			for(int i = 0 ; i < sequence.toString().length(); i++) {
				posicao = i + 1;
				letra =  ((String) sequence).substring(i, posicao);
			
				//Caractere inválido
				if(!letra.matches(VALID_CHARS)) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid DNA.");
				}
				
				if(seqH.equals("")) {
					seqH = letra;
				}else {
					if(seqH.contains(letra)) {
						seqH = seqH.concat(letra);						
					}else if(seqH.length() < VALID_SEQUENCE){
						seqH = letra;
					}
				}
			}
			
			if(seqH.length() == VALID_SEQUENCE) {
				isSimian = true;
				break;
			}				
		}
		
		return isSimian;
	}

	@Override
	public StatsDto getStats() {
		int humanCount = 0;
		int mutantCount = 0;

		List<Dna> allDna = repository.findAll();
		
		if(allDna.isEmpty()){
			statsToDto(humanCount, mutantCount);
		}else {
			for(Dna dna : allDna) {
				if(dna.getDnaType().equals(DnaTypeEnum.HUMAN)) {
					humanCount++;
				}else {
					mutantCount++;	
				}
			}	
		}
		
		return statsToDto(humanCount, mutantCount);
	}

	@Override
	public Dna addDna(Dna dna, Boolean isSimian) {
		dna.setDnaType(isSimian ? DnaTypeEnum.MUTANT : DnaTypeEnum.HUMAN);
		return repository.save(dna);
	}
	
	private StatsDto statsToDto(int humanCount, int mutantCount) {
		StatsDto statsDto = new StatsDto();
		statsDto.setCountHumanDna(humanCount);
		statsDto.setCountMutantDna(mutantCount);
		BigDecimal ratio;
		
		if(humanCount == 0) {
			ratio = new BigDecimal("100");
		}else {
			ratio = new BigDecimal(mutantCount/humanCount);
		}
		 
		statsDto.setRatio(ratio);

		return statsDto;
	}

}
