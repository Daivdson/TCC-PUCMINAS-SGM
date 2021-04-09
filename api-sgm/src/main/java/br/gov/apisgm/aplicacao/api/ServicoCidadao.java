package br.gov.apisgm.aplicacao.api;

import br.gov.apisgm.aplicacao.dominio.Iptu;
import br.gov.apisgm.aplicacao.dominio.Itr;
import br.gov.apisgm.aplicacao.repositorio.RepositorioCidadao;

public class ServicoCidadao {
	
	private RepositorioCidadao repositorio;
	
	public ServicoCidadao(RepositorioCidadao repositorio) {
		super();
		this.repositorio = repositorio;
	}

	public Iptu iptuCidadao(int inscricao) {
		return repositorio.iptu(inscricao);
	}
	
	public Itr itrCidadao(int nirt) {
		return repositorio.itr(nirt);
	}

}
