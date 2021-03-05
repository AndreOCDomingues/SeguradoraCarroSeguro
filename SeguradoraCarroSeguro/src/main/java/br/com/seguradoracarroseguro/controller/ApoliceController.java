package br.com.seguradoracarroseguro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.seguradoracarroseguro.model.Apolice;
import br.com.seguradoracarroseguro.service.ApoliceService;

@RestController
@RequestMapping("/apolices")
public class ApoliceController {

	@Autowired
	private ApoliceService apoliceService;

	// CREATE
	@RequestMapping(value = "/apolice/criar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Apolice criar(@RequestBody Apolice apolice) {
		return apoliceService.criar(apolice);
	}

	// READ
	@RequestMapping(method = RequestMethod.GET)
	public List<Apolice> obterTodas() {
		return this.apoliceService.obterTodas();
	}

	@RequestMapping(value = "/apolice", params = "numero", method = RequestMethod.GET)
	public Apolice obterPorNumero(@RequestParam("numero") Integer numero) {
		return this.apoliceService.obterPorNumero(numero);
	}

	@RequestMapping(value = "/apolice", params = "placaVeiculo", method = RequestMethod.GET)
	public Apolice obterPorPlacaVeiculo(@RequestParam("placaVeiculo") String placaVeiculo) {
		return this.apoliceService.obterPorPlacaVeiculo(placaVeiculo);
	}

	@RequestMapping(value = "/apolice", params = "clienteId", method = RequestMethod.GET)
	public List<Apolice> obterPorCliente(@RequestParam("clienteId") String clienteId) {
		return this.apoliceService.obterPorCliente(clienteId);
	}

	// UPDATE
	@RequestMapping(value = "/apolice/atualizar", params = "id", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Apolice atualizar(@RequestParam("id") Integer id, @RequestBody Apolice apolice) {
		return this.apoliceService.atualizar(id, apolice);
	}

	// DELETE
	@RequestMapping(value = "/apolice/remover", params = "id", method = RequestMethod.DELETE)
	public void remover(@RequestParam("id") Integer id) {
		this.apoliceService.remover(id);
	}

}
