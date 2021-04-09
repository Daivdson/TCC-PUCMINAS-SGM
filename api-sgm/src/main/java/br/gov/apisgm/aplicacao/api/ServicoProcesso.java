package br.gov.apisgm.aplicacao.api;

import java.util.List;

import br.gov.apisgm.aplicacao.dominio.Processo;
import br.gov.apisgm.aplicacao.repositorio.RepositorioProcesso;

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
		return repositorio.novoProcesso(processo);
	}

	public Processo alterarProcesso(Processo processo) {
		
		return repositorio.alterarProcesso(processo);
	}

	public boolean aprovarProcesso(String idProcesso) {
		return repositorio.aprovarProcesso(idProcesso);
	}
	
	

}
