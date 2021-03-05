package br.com.seguradoracarroseguro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.seguradoracarroseguro.model.Cliente;
import br.com.seguradoracarroseguro.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	// CREATE
	@RequestMapping(value = "/cliente/criar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente criar(@RequestBody Cliente cliente) {
		return clienteService.criar(cliente);
	}

	// READ
	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> obterTodos() {
		return this.clienteService.obterTodos();
	}

	@RequestMapping(value = "/cliente", params="id", method = RequestMethod.GET)
	public Cliente obterPorId(@RequestParam("id") String id) {
		return this.clienteService.obterPorId(id);
	}

	@RequestMapping(value = "/cliente", params="nome", method = RequestMethod.GET)
	public Cliente obterPorNome(@RequestParam("nome") String nome) {
		return this.clienteService.obterPorNome(nome);
	}

	@RequestMapping(value = "/cliente", params="cpf", method = RequestMethod.GET)
	public Cliente obterPorCpf(@RequestParam("cpf") String cpf) {
		return this.clienteService.obterPorCpf(cpf);
	}

	// UPDATE
	@RequestMapping(value = "/cliente/atualizar", params="id", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente atualizar(@RequestParam("id") String id, @RequestBody Cliente cliente) {
		return this.clienteService.atualizar(id, cliente);
	}

	// DELETE
	@RequestMapping(value = "/cliente/remover", params="id", method = RequestMethod.DELETE)
	public void remover(@RequestParam("id") String id) {
		this.clienteService.remover(id);
	}

}
