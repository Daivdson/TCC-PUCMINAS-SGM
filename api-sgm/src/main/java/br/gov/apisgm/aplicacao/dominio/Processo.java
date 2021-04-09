package br.gov.apisgm.aplicacao.dominio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Document
public class Processo {
	
	@Id
	private String id;
	private StatusProcesso status;
	private String responsavel;
	private String solicitante;
	private String telefone;
	private String tipoServico;
	private String dataCriacao;
	private String dataAprovacao;
	
	public void aprovado() {
		this.status = StatusProcesso.aprovado;
		this.dataAprovacao = LocalDate.now().toString();
	}
	
	public void analise() {
		this.status = StatusProcesso.analise;
	}
	
	
	public String getDataCriacao() {
		return LocalDate.now().toString();
	}

}
