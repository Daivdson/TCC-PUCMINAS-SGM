package br.gov.apisgm.adaptadores.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.gov.apisgm.aplicacao.api.ServicoCidadao;
import br.gov.apisgm.aplicacao.dominio.Iptu;
import br.gov.apisgm.aplicacao.dominio.Itr;
import springfox.documentation.spring.web.json.Json;


@RestController
@CrossOrigin
@RequestMapping(value = "api/cidadao", produces = { "application/json" })
public class CidadaoCtrl {

	private ServicoCidadao servico;

	public CidadaoCtrl(ServicoCidadao servico) {
		super();
		this.servico = servico;
	}

	@GetMapping(value = { "iptu" }, produces = {"application/json"})
	public @ResponseBody ResponseEntity<?> iptu(Integer inscricao) { 
		
		if(inscricao == null) {
			return new ResponseEntity<>(new Json("{\"error\": \"Inscrição não informado\"}"), HttpStatus.BAD_REQUEST);
		}
		
		Iptu resultado = servico.iptuCidadao(inscricao);
		
		if(resultado == null) {
			return new ResponseEntity<>(new Json("{\"error\": \"iptu não encontrado\"}"), HttpStatus.OK);
		} 
				
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@GetMapping(value = { "itr" }, produces = {"application/json"})
	public @ResponseBody ResponseEntity<?> itr(Integer nirt) { 
		
		if(nirt == null) {
			return new ResponseEntity<>(new Json("{\"error\": \"Itr não informado\"}"), HttpStatus.BAD_REQUEST);
		}
		
		Itr resultado = servico.itrCidadao(nirt);

		if(resultado == null) {
			return new ResponseEntity<>(new Json("{\"error\": \"Itr não encontrado\"}"), HttpStatus.OK);
		} 
		
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
