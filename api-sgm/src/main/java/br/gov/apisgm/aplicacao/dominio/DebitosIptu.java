package br.gov.apisgm.aplicacao.dominio;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DebitosIptu {
	
	private Integer ano;
	private Double valor; 
		
}
