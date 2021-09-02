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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.event.CriarEvent;
import com.gft.model.Desafio;
import com.gft.repository.DesafioRepository;
import com.gft.services.DesafioService;

@RestController
@RequestMapping("/desafios")
@PreAuthorize("hasAuthority('Instrutor')")
public class DesafioController {

	@Autowired
	private DesafioRepository desafioRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private DesafioService desafioService;

	@GetMapping
	public List<Desafio> listar() {
		return desafioRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Desafio> listarPeloCodigo(@PathVariable Long id) {
		Optional<Desafio> desafio = desafioRepository.findById(id);
		if (desafio.isPresent()) {
			return ResponseEntity.of(desafio);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Desafio> criar(@Valid @RequestBody Desafio desafio, HttpServletResponse response) {
		Desafio desafioSalva = desafioRepository.save(desafio);
		publisher.publishEvent(new CriarEvent(this, response, desafioSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(desafioSalva);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Desafio> desafio = desafioRepository.findById(id);
		if (desafio.isPresent()) {
			desafioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Desafio> atualizar(@PathVariable Long id, @Valid @RequestBody Desafio desafio) {
		Desafio desafioSalva = desafioService.atualizar(id, desafio);
		return ResponseEntity.ok(desafioSalva);
	}
}
