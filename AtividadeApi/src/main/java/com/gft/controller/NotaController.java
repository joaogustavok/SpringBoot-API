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

import com.gft.controller.dto.NotaDto;
import com.gft.event.CriarEvent;
import com.gft.model.Nota;
import com.gft.repository.NotaRepository;
import com.gft.services.NotaService;

@RestController
@RequestMapping("/notas")
@PreAuthorize("hasAuthority('Instrutor')")
public class NotaController {

	@Autowired
	private NotaRepository notaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private NotaService notaService;

	@GetMapping
	public List<NotaDto> listar() {
		List<Nota> notas = notaRepository.findAll();
		return NotaDto.converter(notas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<NotaDto> listarPeloCodigo(@PathVariable Long id) {
		Optional<Nota> nota = notaRepository.findById(id);
		if (nota.isPresent()) {
			return ResponseEntity.ok(new NotaDto(nota.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Nota> criar(@Valid @RequestBody Nota nota, HttpServletResponse response) {
		Nota notaSalva = notaRepository.save(nota);
		publisher.publishEvent(new CriarEvent(this, response, notaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(notaSalva);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Nota> nota = notaRepository.findById(id);
		if (nota.isPresent()) {
			notaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Nota> atualizar(@PathVariable Long id, @Valid @RequestBody Nota nota) {
		Nota notaSalva = notaService.atualizar(id, nota);
		return ResponseEntity.ok(notaSalva);
	}

}
