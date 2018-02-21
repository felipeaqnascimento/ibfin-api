package br.com.ibrowse.dto;

import java.util.List;

public class ResponseDTO {
	private boolean sucesso;
	private List<?> valor;
	private String mensagem;

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public List<?> getValor() {
		return valor;
	}

	public void setValor(List<?> valor) {
		this.valor = valor;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
