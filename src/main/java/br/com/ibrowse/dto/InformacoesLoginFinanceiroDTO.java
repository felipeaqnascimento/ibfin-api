package br.com.ibrowse.dto;

/**
 * Classe que será enviada para o cliente quando realizar um login com sucesso.
 * Essa classe deve conter atributos publicos. Geralmente atributos que serão
 * apresentados no cliente (aplicacao front-end). NOTA: Nao inclua atributos
 * "sigilosos" nessa classe
 * 
 * @author joao.teodoro
 *
 */
public class InformacoesLoginFinanceiroDTO {

	// private String token;
	private String nmUsuario;
	private String cpf;
	//private String oidUsuario;
	private String dsEmail;

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/*
	 * public String getToken() { return token; }
	 * 
	 * public void setToken(String token) { this.token = token; }
	 */

	/*public String getOidUsuario() {
		return oidUsuario;
	}

	public void setOidUsuario(String oidUsuario) {
		this.oidUsuario = oidUsuario;
	}*/

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

}
