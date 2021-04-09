package br.gov.apisgm.adaptadores.repositorio.cidadao;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.gov.apisgm.aplicacao.dominio.Processo;

public interface PersistenciaProcesso extends MongoRepository<Processo, String> {

}
