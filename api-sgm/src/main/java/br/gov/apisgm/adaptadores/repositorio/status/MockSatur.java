package br.gov.apisgm.adaptadores.repositorio.status;

import java.util.Arrays;
import java.util.List;

import br.gov.apisgm.aplicacao.dominio.DebitosIptu;
import br.gov.apisgm.aplicacao.dominio.Iptu;
import br.gov.apisgm.aplicacao.dominio.Itr;

public class MockSatur {
	
	public List<Iptu> iptus(){
		return Arrays.asList(
				Iptu.builder().debitos(debitos()).endereco("CENTRO URBANO QD 102 CJ 2 AP 1300").inscricao(12345).nomeRazaoSocial("João de Almeidas lagos").build(),
				Iptu.builder().debitos(debitos()).endereco("Qn 1502 QD 102 CJ 2 casa 22").inscricao(123456).nomeRazaoSocial("Maria dos Milagres dos Santos").build(),
				Iptu.builder().debitos(debitos()).endereco("Qn 305 URBANO QD 102 CJ 2 casa 8").inscricao(123457).nomeRazaoSocial("jose Silveira Neto").build());
	}
	
	public List<Itr> itrMock(){
		return Arrays.asList(
				Itr.builder().debitos(debitos()).endereco("Qn 1502 QD 102 CJ 2 casa 22").nirf(12345).nomeRazaoSocial("Maria Tereza de Jesus").build(),
				Itr.builder().debitos(debitos()).endereco("Qn 309 QD 102 CJ 2 casa 22").nirf(123456).nomeRazaoSocial("Raimundo dos Santos Oliveira").build(),
				Itr.builder().debitos(debitos()).endereco("Qn 443 QD 102 CJ 2 casa 22").nirf(123467).nomeRazaoSocial("João Nonato de Campos").build()
				);
	}
	
	private List<DebitosIptu> debitos(){
		return Arrays.asList(
				DebitosIptu.builder().ano(2019).valor(479.22).build(),
				DebitosIptu.builder().ano(2020).valor(485.13).build(),
				DebitosIptu.builder().ano(2021).valor(495.10).build());
	}

}
