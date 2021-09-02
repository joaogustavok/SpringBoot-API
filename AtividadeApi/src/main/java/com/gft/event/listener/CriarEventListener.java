package com.gft.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.event.CriarEvent;

@Component
public class CriarEventListener implements ApplicationListener<CriarEvent> {

	@Override
	public void onApplicationEvent(CriarEvent criarEvent) {
		HttpServletResponse response = criarEvent.getResponse();
		Long id = criarEvent.getId();
		adiocionarHeaderLocation(response, id);
	}

	private void adiocionarHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
