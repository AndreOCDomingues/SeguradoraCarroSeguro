package br.com.seguradoracarroseguro.service;

import java.util.List;

import br.com.seguradoracarroseguro.model.Apolice;
import br.com.seguradoracarroseguro.model.ApoliceCompleta;

public interface ApoliceService {
	// CREATE
	public Apolice criar(Apolice apolice);

	// READ
	public List<Apolice> obterTodas();
	public Apolice obterPorNumero(Integer numero);
	public Apolice obterPorPlacaVeiculo(String placaVeiculo);
	public List<Apolice> obterPorCliente(String clienteId);
	
	public ApoliceCompleta consultaApoliceCompletaPorNumero(Integer numero);

	// UPDATE
	public Apolice atualizar(Integer id, Apolice apolice);

	// DELETE
	public void remover(Integer id);

}
