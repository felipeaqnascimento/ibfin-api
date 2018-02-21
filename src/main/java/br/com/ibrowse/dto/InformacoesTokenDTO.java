package br.com.ibrowse.dto;

/**
 * Classe que sera transformada em parte do token. Devem ser inclusos nessa
 * classe, atributos relacionados ao usuário logado. Esses atributos podem ser
 * utilziados em consultas, sem a necessidade de serem enviados no corpo de uma
 * requisicao
 * 
 * @author joao.teodoro
 * @since 2017
 *
 */
public class InformacoesTokenDTO {
	private String oidUsuario;
	private String cpf;

	public String getOidUsuario() {
		return oidUsuario;
	}

	public void setOidUsuario(String oidUsuario) {
		this.oidUsuario = oidUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
