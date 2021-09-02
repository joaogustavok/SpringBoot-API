package com.gft.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gft.model.Starter;
import com.gft.repository.StarterRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private StarterRepository starterRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Starter> starter = starterRepository.findByEmail(username);
		if (starter.isPresent()) {
			return starter.get();
		}
		throw new UsernameNotFoundException("Dados Invalidos");

	}

}
