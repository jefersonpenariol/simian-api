package br.com.jeferson.simian.api.controllers;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jeferson.simian.api.entities.Dna;
import br.com.jeferson.simian.api.services.DnaService;

@RunWith(SpringRunner.class)
@WebMvcTest(SimianController.class)
public class SimianControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean	
	private DnaService service;

	@Test
	public void testIsSimian() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Dna dna = new Dna();
		String[] mutantDna = {"AAAAGA", "CCCCGC", "TCATGT", "AAAAGG", "CCCCTA", "TCACTA"};
		dna.setDnaId(mutantDna);
		
		BDDMockito.given(service.isSimian(mutantDna)).willReturn(true);
		
		mvc.perform(post("/simian")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dna)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testIsNotSimian() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Dna dna = new Dna();
		String[] mutantDna = {"ACAAGA", "CACCGC", "TCATGT", "CAAAGG", "CACCTA", "TCACTA"};
		dna.setDnaId(mutantDna);
		
		BDDMockito.given(service.isSimian(mutantDna)).willReturn(false);
		
		mvc.perform(post("/simian")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dna)))
				.andExpect(status().isForbidden());
	}
	
	@Test
	public void testIsInvalidDna() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Dna dna = new Dna();
		String[] mutantDna = {"AXAAGA", "CACCGC", "TCATGT", "CAAAGG", "CACCTA", "TCACTA"};
		dna.setDnaId(mutantDna);
		
		BDDMockito.given(service.isSimian(mutantDna)).willThrow(ResponseStatusException.class);
		
		mvc.perform(post("/simian")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dna)))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
	}
	
	@Test
	public void testGetStats() throws Exception {		
		mvc.perform(get("/stats")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());		
	}
}
