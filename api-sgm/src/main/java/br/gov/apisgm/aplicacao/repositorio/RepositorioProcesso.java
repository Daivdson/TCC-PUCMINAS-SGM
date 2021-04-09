package br.gov.apisgm.aplicacao.repositorio;

import java.util.List;

import br.gov.apisgm.aplicacao.dominio.Processo;

public interface RepositorioProcesso {

	List<Processo> todosProcessos();

	Processo processoPorId(String idProcesso);

	Processo novoProcesso(Processo processo);

	Processo alterarProcesso(Processo processo);

}
