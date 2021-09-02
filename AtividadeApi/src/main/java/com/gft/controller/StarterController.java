package com.gft.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.controller.dto.StarterDto;
import com.gft.event.CriarEvent;
import com.gft.model.Perfil;
import com.gft.model.Starter;
import com.gft.repository.PerfilRepository;
import com.gft.repository.StarterRepository;
import com.gft.services.StarterService;

@RestController
@RequestMapping("/starters")
@PreAuthorize("hasAuthority('Instrutor')")
public class StarterController {

	@Autowired
	private StarterRepository starterRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private StarterService starterService;

	@GetMapping
	public List<StarterDto> listar() {
		List<Starter> starters = starterRepository.findAll();
		return StarterDto.converter(starters);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StarterDto> listarPeloCodigo(@PathVariable Long id) {
		Optional<Starter> starter = starterRepository.findById(id);
		if (starter.isPresent()) {
			return ResponseEntity.ok(new StarterDto(starter.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<StarterDto> atualizar(@PathVariable Long id, @Valid @RequestBody Starter starter) {
		Starter starterSalva = starterService.atualizar(id, starter);
		return ResponseEntity.ok(new StarterDto(starterSalva));
	}

	@PostMapping
	public ResponseEntity<StarterDto> criar(@Valid @RequestBody Starter starter, HttpServletResponse response) {
		starter.setSenha(new BCryptPasswordEncoder().encode(starter.getQuatroLetras()));
		Perfil perfil = perfilRepository.getById((long) 1);
		starter.setPerfil(perfil);
		Starter starterSalva = starterRepository.save(starter);
		publisher.publishEvent(new CriarEvent(this, response, starterSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new StarterDto(starterSalva));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Starter> starter = starterRepository.findById(id);
		if (starter.isPresent()) {
			starterRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
