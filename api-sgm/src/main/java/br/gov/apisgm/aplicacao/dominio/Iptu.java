package br.gov.apisgm.aplicacao.dominio;


import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Iptu {

	private Integer inscricao;
	private String nomeRazaoSocial;
	private String endereco;
	List<DebitosIptu> debitos;
	
}
