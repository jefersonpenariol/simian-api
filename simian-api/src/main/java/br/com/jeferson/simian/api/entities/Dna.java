package br.com.jeferson.simian.api.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.jeferson.simian.api.enums.DnaTypeEnum;

@Table(name = "DNA")
public class Dna {
	
	@Id
	@Column(name = "DNA_ID")
	@JsonProperty("dna")
	private String[] dnaId;

	@Column(name = "DNA_TYPE", nullable = false)
	@JsonProperty("dna_type")
	private DnaTypeEnum dnaType;

	public String[] getDnaId() {
		return dnaId;
	}

	public void setDnaId(String[] dnaId) {
		this.dnaId = dnaId;
	}

	public DnaTypeEnum getDnaType() {
		return dnaType;
	}

	public void setDnaType(DnaTypeEnum dnaType) {
		this.dnaType = dnaType;
	}
	
	
}
