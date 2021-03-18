package br.com.jeferson.simian.api.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsDto {
	
	@JsonProperty("count_mutant_dna")
	private int countMutantDna;
	
	@JsonProperty("count_human_dna")
	private int countHumanDna;
	
	@JsonProperty("ratio")
	private BigDecimal ratio;
}
