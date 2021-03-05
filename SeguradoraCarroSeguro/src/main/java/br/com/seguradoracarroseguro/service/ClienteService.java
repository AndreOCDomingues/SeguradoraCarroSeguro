package br.com.seguradoracarroseguro.service;

import java.util.List;

import br.com.seguradoracarroseguro.model.Cliente;

public interface ClienteService {
	//CREATE
	public Cliente criar(Cliente cliente);
	
	//READ
	public List<Cliente> obterTodos();
	public Cliente obterPorId(String id);
	public Cliente obterPorNome(String nome);
	public Cliente obterPorCpf(String cpf);
	
	//UPDATE
	public Cliente atualizar(String id, Cliente cliente);
	
	//DELETE
	public boolean remover(String bjectId);

	

}
