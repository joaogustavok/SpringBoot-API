package com.gft.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class CriarEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private Long id;

	public CriarEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.id = id;

	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getId() {
		return id;
	}

}
