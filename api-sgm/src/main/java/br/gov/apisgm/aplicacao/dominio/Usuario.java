package br.gov.apisgm.aplicacao.dominio;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Usuario {
	private String _id;
	private String nome;
	private String email;
	private Role roles;
}
