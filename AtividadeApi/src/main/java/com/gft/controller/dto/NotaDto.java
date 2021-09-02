package com.gft.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.gft.model.Nota;

public class NotaDto {

	private Long id;
	private Long submissaoId;
	private Long submissaoDesafioId;
	private String submissaoDesafio;
	private Long starterId;
	private String starterNome;
	private String starterEmail;
	private Integer qualidade;
	private Integer quantidade;

	public NotaDto(Nota nota) {
		this.id = nota.getId();
		this.submissaoId = nota.getSubmissao().getId();
		this.starterId = nota.getSubmissao().getStarter().getId();
		this.starterNome = nota.getSubmissao().getStarter().getNome();
		this.starterEmail = nota.getSubmissao().getStarter().getEmail();
		this.submissaoDesafioId = nota.getSubmissao().getDesafio().getId();
		this.submissaoDesafio = nota.getSubmissao().getDesafio().getNome();
		this.qualidade = nota.getQualidade();
		this.quantidade = nota.getQuantidade();

	}

	public Long getId() {
		return id;
	}

	public Long getSubmissaoId() {
		return submissaoId;
	}

	public Long getSubmissaoDesafioId() {
		return submissaoDesafioId;
	}

	public String getSubmissaoDesafio() {
		return submissaoDesafio;
	}

	public Long getStarterId() {
		return starterId;
	}

	public String getStarterNome() {
		return starterNome;
	}

	public String getStarterEmail() {
		return starterEmail;
	}

	public Integer getQualidade() {
		return qualidade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public static List<NotaDto> converter(List<Nota> notas) {
		return notas.stream().map(NotaDto::new).collect(Collectors.toList());
	}

}
