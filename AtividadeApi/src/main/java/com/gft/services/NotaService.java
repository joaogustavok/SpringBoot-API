package com.gft.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.model.Nota;
import com.gft.repository.NotaRepository;

@Service
public class NotaService {

	@Autowired
	private NotaRepository notaRepository;

	public Nota atualizar(Long id, @Valid Nota nota) {
		Nota notaSalva = buscarNota(id);
		BeanUtils.copyProperties(nota, notaSalva, "id");
		return notaRepository.save(notaSalva);
	}

	public Nota buscarNota(Long id) {
		Optional<Nota> notaSalva = notaRepository.findById(id);
		if (notaSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return notaSalva.get();
	}
}
