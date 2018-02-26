package br.com.ibrowse.bean;

import org.springframework.security.core.GrantedAuthority;

public class RoleUser implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7282112574532362784L;

	@Override
	public String getAuthority() {
		return "ADMIN";
	}

}
