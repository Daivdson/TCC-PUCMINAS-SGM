package br.gov.apisgm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gov.apisgm.aplicacao.api.ServicoCidadao;
import br.gov.apisgm.aplicacao.api.ServicoProcesso;
import br.gov.apisgm.aplicacao.repositorio.RepositorioCidadao;
import br.gov.apisgm.aplicacao.repositorio.RepositorioProcesso;

@Configuration
public class ServicoConfiguracao {
	
	@Bean
	public ServicoCidadao servicoCidadao(RepositorioCidadao repositorio) {
		return new ServicoCidadao(repositorio); 
	}
	
	@Bean
	public ServicoProcesso servicoProcesso(RepositorioProcesso repositorio) {
		return new ServicoProcesso(repositorio); 
	}

}
