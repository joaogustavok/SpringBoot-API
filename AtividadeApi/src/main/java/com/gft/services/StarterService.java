package com.gft.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gft.model.Perfil;
import com.gft.model.Starter;
import com.gft.repository.PerfilRepository;
import com.gft.repository.StarterRepository;

@Service
public class StarterService {

	@Autowired
	private StarterRepository starterRepository;
	@Autowired
	private PerfilRepository perfilRepository;

	public Starter atualizar(Long id, @Valid Starter starter) {
		Starter starterSalva = buscarStarter(id);
		starter.setSenha(new BCryptPasswordEncoder().encode(starter.getQuatroLetras()));
		Perfil perfil = perfilRepository.getById((long) 1);
		starter.setPerfil(perfil);
		BeanUtils.copyProperties(starter, starterSalva, "id");
		return starterRepository.save(starterSalva);
	}

	public Starter buscarStarter(Long id) {
		Optional<Starter> pessoaSalva = starterRepository.findById(id);
		if (pessoaSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva.get();
	}
}
