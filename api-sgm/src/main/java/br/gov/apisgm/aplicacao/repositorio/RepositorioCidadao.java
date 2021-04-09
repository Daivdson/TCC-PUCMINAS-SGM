package br.gov.apisgm.aplicacao.repositorio;

import br.gov.apisgm.aplicacao.dominio.Iptu;
import br.gov.apisgm.aplicacao.dominio.Itr;

public interface RepositorioCidadao {

	Iptu iptu(int inscricao);

	Itr itr(int inscricao);

}
