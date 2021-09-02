package com.gft.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.model.Submissao;
import com.gft.repository.SubmissaoRepository;

@Service
public class SubmissaoService {

	@Autowired
	private SubmissaoRepository submissaoRepository;

	public Submissao atualizar(Long id, @Valid Submissao submissao) {
		Submissao submissaoSalva = buscarSubmissao(id);
		BeanUtils.copyProperties(submissao, submissaoSalva, "id");
		return submissaoRepository.save(submissaoSalva);
	}

	public Submissao buscarSubmissao(Long id) {
		Optional<Submissao> submissaoSalva = submissaoRepository.findById(id);
		if (submissaoSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return submissaoSalva.get();
	}

}
