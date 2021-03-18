package br.com.jeferson.simian.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jeferson.simian.api.dtos.StatsDto;
import br.com.jeferson.simian.api.entities.Dna;
import br.com.jeferson.simian.api.services.DnaService;

@RestController
public class SimianController {
	
	@Autowired
	private DnaService service;
	
	@PostMapping("/simian")
	public ResponseEntity<Dna> isSimian(@RequestBody Dna dna){
		if(service.isSimian(dna.getDnaId())) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@GetMapping("/stats")
	public ResponseEntity<StatsDto> getStats(){
		return ResponseEntity.ok(service.getStats());
	}
}
