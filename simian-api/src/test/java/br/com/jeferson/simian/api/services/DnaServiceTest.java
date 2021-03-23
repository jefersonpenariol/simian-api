package br.com.jeferson.simian.api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import br.com.jeferson.simian.api.dtos.StatsDto;
import br.com.jeferson.simian.api.entities.Dna;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DnaServiceTest {
	
	@Autowired
	private DnaService service;

	@Test
	public void testIsSimian() {
		String[] dna = {"AAAAGA", "CCCCGC", "TCATGT", "AAAAGG", "CCCCTA", "TCACTA"};
		assertTrue(service.isSimian(dna));
	}
	
	@Test
	public void testIsNotSimian() {
		String[] dna = {"ACAAGA", "CACCGC", "TCATGT", "ACAAGG", "CACCTA", "TCACTA"};
		assertFalse(service.isSimian(dna));
	}
	
	@Test
	public void testInvalidDna() {
		String[] dna = {"ACAAGA", "CACCGC", "TCATGT", "AXAAGG", "CACCTA", "TCACTA"};
		ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> service.isSimian(dna),
				"Invalid DNA.");

		assertTrue(thrown.getMessage().contains("Invalid DNA."));
	}
	
	@Test
	public void testAddDnaMutant() {
		String[] mutantDna = {"ACAAGA", "CACCGC", "TCATGT", "AAAAGG", "CACCTA", "TCACTA"};
		Dna dna = new Dna();
		dna.setDnaId(mutantDna);
		
		assertNotNull(service.addDna(dna, true));
	}
	
	@Test
	public void testAddDnaHuman() {
		String[] humanDna = {"ACAAGA", "CACCGC", "TCATGT", "ACAAGG", "CACCTA", "TCACTA"};
		Dna dna = new Dna();
		dna.setDnaId(humanDna);
		
		assertNotNull(service.addDna(dna, false));
	}
	
	@Test
	public void testGetStats() {
		String[] mutantDna = {"ACAAGA", "CACCGC", "TCATGT", "AAAAGG", "CACCTA", "TCACTA"};
		Dna dnaM = new Dna();
		dnaM.setDnaId(mutantDna);	
		service.addDna(dnaM, true);
		
		String[] humantDna = {"ACAAGA", "CACCGC", "TCATGT", "ACAAGG", "CACCTA", "TCACTA"};
		Dna dnaH = new Dna();
		dnaH.setDnaId(humantDna);
		service.addDna(dnaH, false);
		
		StatsDto statDto = service.getStats();
		
		assertEquals(BigDecimal.ONE, statDto.getRatio());
	}
	
}
