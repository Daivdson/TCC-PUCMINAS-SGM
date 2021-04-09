package br.gov.apisgm.aplicacao.api;

import java.util.List;

import br.gov.apisgm.aplicacao.dominio.Processo;
import br.gov.apisgm.aplicacao.repositorio.RepositorioProcesso;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServicoProcesso {
	
	private RepositorioProcesso repositorio;
	
	public ServicoProcesso(RepositorioProcesso repositorio) {
		super();
		this.repositorio = repositorio;
	}

	public List<Processo> todosProcessos() {
		System.out.println("todos processos");
		return repositorio.todosProcessos();
	}

	public Processo processoPorId(String idProcesso) {
		System.out.println("Processo "+idProcesso);
		return repositorio.processoPorId(idProcesso);
	}

	public Processo novoProcesso(Processo processo) {
		System.out.println("novo processo");
		processo.analise();
		return repositorio.novoProcesso(processo);
	}

	public Processo alterarProcesso(Processo processo) {
		processo.analise();
		return repositorio.alterarProcesso(processo);
	}

	public boolean aprovarProcesso(String idProcesso) {
		
		try {
			Processo processo = processoPorId(idProcesso);
			
			processo.aprovado();
			
			alterarProcesso(processo);
			
			return true;
			
		} catch (Exception e) {
			log.error("Erro ao aprovar processo: "+e.getMessage());
			return false;
		}
	}
	
	

}
