package com.gft.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	private Submissao submissao;

	@Range(min = 1, max = 3)
	@NotNull
	private Integer qualidade;
	@NotNull
	@Range(min = 1, max = 3)
	private Integer quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Submissao getSubmissao() {
		return submissao;
	}

	public void setSubmissao(Submissao submissao) {
		this.submissao = submissao;
	}

	public Integer getQualidade() {
		return qualidade;
	}

	public void setQualidade(Integer qualidade) {
		this.qualidade = qualidade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
