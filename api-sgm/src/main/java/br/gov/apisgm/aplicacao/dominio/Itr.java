package br.gov.apisgm.aplicacao.dominio;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class Itr {
	private Integer nirf;
	private String endereco;
	private String nomeRazaoSocial;
	List<DebitosIptu> debitos;

}
