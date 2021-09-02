package com.gft.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.gft.model.Submissao;

public class SubmissaoDto {
	private Long id;
	private Long starterId;
	private String starterNome;
	private String starterEmail;
	private Long desafioId;
	private String desafioNome;
	private String repositorio;

	public Long getId() {
		return id;
	}

	public String getStarterNome() {
		return starterNome;
	}

	public String getStarterEmail() {
		return starterEmail;
	}

	public Long getStarterId() {
		return starterId;
	}

	public Long getDesafioId() {
		return desafioId;
	}

	public String getDesafioNome() {
		return desafioNome;
	}

	public String getRepositorio() {
		return repositorio;
	}

	public SubmissaoDto(Submissao submissao) {
		this.id = submissao.getId();
		this.starterId = submissao.getStarter().getId();
		this.starterNome = submissao.getStarter().getNome();
		this.starterEmail = submissao.getStarter().getEmail();
		this.desafioId = submissao.getDesafio().getId();
		this.desafioNome = submissao.getDesafio().getNome();
		this.repositorio = submissao.getRepositorio();
	}

	public static List<SubmissaoDto> converter(List<Submissao> submissao) {
		return submissao.stream().map(SubmissaoDto::new).collect(Collectors.toList());
	}

}
