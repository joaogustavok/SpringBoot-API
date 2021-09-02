package com.gft.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.model.Desafio;
import com.gft.repository.DesafioRepository;

@Service
public class DesafioService {

	@Autowired
	private DesafioRepository desafioRepository;

	public Desafio atualizar(Long id, @Valid Desafio desafio) {
		Desafio desafioSalva = buscarDesafio(id);
		BeanUtils.copyProperties(desafio, desafioSalva, "id");
		return desafioRepository.save(desafioSalva);
	}

	public Desafio buscarDesafio(Long id) {
		Optional<Desafio> desafioSalva = desafioRepository.findById(id);
		if (desafioSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return desafioSalva.get();
	}

}
