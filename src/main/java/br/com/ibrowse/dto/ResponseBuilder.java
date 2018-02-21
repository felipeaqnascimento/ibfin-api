package br.com.ibrowse.dto;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseBuilder {

	private ResponseDTO response;

	public ResponseBuilder() {
		this.response = new ResponseDTO();
	}

	public ResponseBuilder comMensagem(String mensagem) {
		this.response.setMensagem(mensagem);
		return this;
	}

	public ResponseBuilder comSucesso() {
		this.response.setSucesso(true);
		return this;
	}

	public ResponseBuilder semSucesso() {
		this.response.setSucesso(false);
		return this;
	}

	public ResponseBuilder comValor(List<?> valor) {
		this.response.setValor(valor);
		return this;
	}

	public String build() throws JsonProcessingException {

		return new ObjectMapper().writeValueAsString(response);
	}
}