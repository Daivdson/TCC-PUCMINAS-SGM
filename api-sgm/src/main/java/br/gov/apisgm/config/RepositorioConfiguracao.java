package br.gov.apisgm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gov.apisgm.adaptadores.repositorio.cidadao.ManipulacaoProcesso;
import br.gov.apisgm.adaptadores.repositorio.cidadao.PersistenciaProcesso;
import br.gov.apisgm.adaptadores.repositorio.status.IntegracaoSatur;
import br.gov.apisgm.aplicacao.repositorio.RepositorioCidadao;
import br.gov.apisgm.aplicacao.repositorio.RepositorioProcesso;

@Configuration
public class RepositorioConfiguracao {

//	@Value("${spring.elasticsearch.jest.uris}")
//	private String[] esUrls;
//
//	@Autowired
//	ResourceLoader resourceLoader;
	
	@Bean
	public RepositorioCidadao repositorioCidadao() {
		return new IntegracaoSatur();
	}
	
	@Bean
	public RepositorioProcesso repositorioProcesso(PersistenciaProcesso persistenciaProcesso) {
		return new ManipulacaoProcesso(persistenciaProcesso);
	}

}
