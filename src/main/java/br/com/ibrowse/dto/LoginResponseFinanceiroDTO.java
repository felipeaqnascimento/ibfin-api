package br.com.ibrowse.dto;

public class LoginResponseFinanceiroDTO {

	private boolean sucesso;
	private InformacoesLoginFinanceiroDTO informacoesUsuario;
	private String mensagem;
	private String token;

	public InformacoesLoginFinanceiroDTO getInformacoesUsuario() {
		return informacoesUsuario;
	}

	public void setInformacoesUsuario(InformacoesLoginFinanceiroDTO informacoesUsuario) {
		this.informacoesUsuario = informacoesUsuario;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
