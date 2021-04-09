package br.gov.apisgm.aplicacao.dominio;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	role_admin, role_moderador;
	
	public String getAuthority() {
	    return name();
	  }
	
}
