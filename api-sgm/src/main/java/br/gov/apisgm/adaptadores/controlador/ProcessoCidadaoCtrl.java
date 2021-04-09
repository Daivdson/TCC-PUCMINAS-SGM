package br.gov.apisgm.adaptadores.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.gov.apisgm.aplicacao.api.ServicoProcesso;
import br.gov.apisgm.aplicacao.dominio.Processo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import springfox.documentation.spring.web.json.Json;


@RestController
@RequestMapping(value = "api/processo", produces = { "application/json" })
public class ProcessoCidadaoCtrl {

	private ServicoProcesso servico;

	public ProcessoCidadaoCtrl(ServicoProcesso servico) {
		super();
		this.servico = servico;
	}

	@GetMapping(value = { "/" })
	public @ResponseBody ResponseEntity<?> processo() { 
		List<Processo> resultado = servico.todosProcessos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = { "/{idProcesso}" })
	public @ResponseBody ResponseEntity<?> processoPorId(@PathVariable("") String idProcesso) {
		if(idProcesso == null) {
			return new ResponseEntity<>(new Json("{\"Id do processo não informado\"}"), HttpStatus.BAD_REQUEST);
		}
		Processo resultado = servico.processoPorId(idProcesso);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@PostMapping(value = { "/" })
	public @ResponseBody ResponseEntity<?> novoProcesso(@RequestBody Processo processo) {
		Processo resultado = servico.novoProcesso(processo);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@PutMapping(value = { "/" })
	public @ResponseBody ResponseEntity<?> alterarProcesso(@RequestBody Processo processo) {
		Processo resultado = servico.alterarProcesso(processo);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@PatchMapping(value = { "/{idProcesso}/aprovar" })
	@PreAuthorize("hasRole('role_admin')")
	@ApiOperation(value = "${ProcessoCidadaoCtrl.aprovarProceso}", response = ResponseEntity.class, authorizations = { @Authorization(value="apiKey") })
	public @ResponseBody ResponseEntity<?> aprovarProceso(@ApiParam("Username") @PathVariable String idProcesso) {
		if(idProcesso == null) {
			return new ResponseEntity<>(new Json("{\"Id do processo não informado\"}"), HttpStatus.BAD_REQUEST);
		}
		if(servico.aprovarProcesso(idProcesso)) {			
			return new ResponseEntity<>("Processo aprovado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Erro ao aprovar processo", HttpStatus.OK);
		}
			
	}

}
