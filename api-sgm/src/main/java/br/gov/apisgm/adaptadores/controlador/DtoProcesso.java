package br.gov.apisgm.adaptadores.controlador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoProcesso {
	
	private String id;
    private String responsavel;
    private String solicitante;
    private String telefone;
    private String tipoServico;

}
