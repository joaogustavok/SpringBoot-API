package com.gft.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Embedded;

import com.gft.model.Endereco;
import com.gft.model.Starter;

public class StarterDto {

	private Long id;
	private String nome;
	private String email;
	private String quatroLetras;
	private String linguagem;
	private String telefone;
	@Embedded
	private Endereco endereco;

	public StarterDto(Starter starter) {
		this.id = starter.getId();
		this.nome = starter.getNome();
		this.email = starter.getEmail();
		this.quatroLetras = starter.getQuatroLetras();
		this.linguagem = starter.getLinguagem();
		this.telefone = starter.getTelefone();
		this.endereco = starter.getEndereco();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getQuatroLetras() {
		return quatroLetras;
	}

	public String getLinguagem() {
		return linguagem;
	}

	public String getTelefone() {
		return telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public String getEmail() {
		return email;
	}

	public static List<StarterDto> converter(List<Starter> starters) {
		return starters.stream().map(StarterDto::new).collect(Collectors.toList());
	}

}
