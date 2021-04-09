package br.gov.apisgm.adaptadores.repositorio.status;

import java.util.List;
import java.util.stream.Collectors;

import br.gov.apisgm.aplicacao.dominio.Iptu;
import br.gov.apisgm.aplicacao.dominio.Itr;
import br.gov.apisgm.aplicacao.repositorio.RepositorioCidadao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IntegracaoSatur implements RepositorioCidadao{

	@Override
	public Iptu iptu(int inscricao) {
		MockSatur mock = new MockSatur();
		List<Iptu> iptuCidadao = mock.iptus().stream()
				.filter(v -> v.getInscricao().equals(inscricao))
				.collect(Collectors.toList());
		return iptuCidadao.size() > 0 ? iptuCidadao.get(0) : null;
	}

	@Override
	public Itr itr(int nirt) {
		MockSatur mock = new MockSatur();
		List<Itr> itrCidadao = mock.itrMock().stream()
				.filter(v -> v.getNirf().equals(nirt))
				.collect(Collectors.toList());
		return itrCidadao.size() > 0 ? itrCidadao.get(0) : null;
	}
	
	

}
