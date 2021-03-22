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

	public int getCountMutantDna() {
		return countMutantDna;
	}

	public void setCountMutantDna(int countMutantDna) {
		this.countMutantDna = countMutantDna;
	}

	public int getCountHumanDna() {
		return countHumanDna;
	}

	public void setCountHumanDna(int countHumanDna) {
		this.countHumanDna = countHumanDna;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	
	
}
