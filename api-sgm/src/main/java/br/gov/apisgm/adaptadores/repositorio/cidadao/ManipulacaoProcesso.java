package br.gov.apisgm.adaptadores.repositorio.cidadao;

import java.util.List;

import br.gov.apisgm.aplicacao.dominio.Processo;
import br.gov.apisgm.aplicacao.repositorio.RepositorioProcesso;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManipulacaoProcesso implements RepositorioProcesso {
	
	private PersistenciaProcesso reposi;
	
	public ManipulacaoProcesso(PersistenciaProcesso reposi) {
		this.reposi = reposi;
	}

	@Override
	public List<Processo> todosProcessos() {
		return reposi.findAll();
	}

	@Override
	public Processo processoPorId(String id) {
		Processo resultado = reposi.findById(id).get();
		return resultado;
	}

	@Override
	public Processo novoProcesso(Processo processo) {
		return reposi.insert(processo);
	}

	@Override
	public Processo alterarProcesso(Processo processo) {
		return reposi.save(processo);
	}

	@Override
	public boolean aprovarProcesso(String idProcesso) {
		
		try {
			Processo processo = reposi.findById(idProcesso).get();
			
			processo.aprovado();
			
			reposi.save(processo);
			
			return true;
			
		} catch (Exception e) {
			log.error("Erro ao aprovar processo: "+e.getMessage());
			return false;
		}
	}

}
