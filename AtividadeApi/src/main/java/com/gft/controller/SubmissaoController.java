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

import com.gft.controller.dto.SubmissaoDto;
import com.gft.event.CriarEvent;
import com.gft.model.Submissao;
import com.gft.repository.SubmissaoRepository;
import com.gft.services.SubmissaoService;

@RestController
@RequestMapping("/submissao")
public class SubmissaoController {

	@Autowired
	private SubmissaoRepository submissaoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private SubmissaoService submissaoService;
	
	@PreAuthorize("hasAuthority('Instrutor')")
	@GetMapping
	public List<SubmissaoDto> listar() {
		List<Submissao> submissao = submissaoRepository.findAll();
		return SubmissaoDto.converter(submissao);
	}
	@PreAuthorize("hasAuthority('Instrutor')")
	@GetMapping("/{id}")
	public ResponseEntity<SubmissaoDto> listarPeloCodigo(@PathVariable Long id) {
		Optional<Submissao> submissao = submissaoRepository.findById(id);
		submissao.stream().toList();
		if (submissao.isPresent()) {
			return ResponseEntity.ok(new SubmissaoDto(submissao.get()));
		}
		return ResponseEntity.notFound().build();
	}
	@PreAuthorize("hasAuthority('Instrutor') or hasAuthority('Starter')")
	@PostMapping
	public ResponseEntity<SubmissaoDto> criar(@Valid @RequestBody Submissao submissao, HttpServletResponse response) {
		Submissao submissaoSalva = submissaoRepository.save(submissao);
		publisher.publishEvent(new CriarEvent(this, response, submissaoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new SubmissaoDto(submissaoSalva));
	}
	@PreAuthorize("hasAuthority('Instrutor')")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Submissao> submissao = submissaoRepository.findById(id);
		if (submissao.isPresent()) {
			submissaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@PreAuthorize("hasAuthority('Instrutor')")
	@PutMapping("/{id}")
	public ResponseEntity<SubmissaoDto> atualizar(@PathVariable Long id, @Valid @RequestBody Submissao submissao) {
		Submissao submissaoSalva = submissaoService.atualizar(id, submissao);
		return ResponseEntity.ok(new SubmissaoDto(submissaoSalva));
	}

}
