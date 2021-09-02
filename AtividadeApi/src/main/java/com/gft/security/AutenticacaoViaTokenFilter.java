package com.gft.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gft.model.Starter;
import com.gft.repository.StarterRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private StarterRepository starterRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, StarterRepository starterRepository) {
		this.tokenService = tokenService;
		this.starterRepository = starterRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);

		boolean valido = tokenService.isTokenValido(token);
		if (valido) {
			autenticarCliente(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idStarter = tokenService.getIdStarter(token);
		Starter starter = starterRepository.findById(idStarter).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(starter, null,
				starter.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
