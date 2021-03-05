package br.com.seguradoracarroseguro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.seguradoracarroseguro.model.Apolice;
import br.com.seguradoracarroseguro.model.Cliente;

public interface ApoliceRepository extends MongoRepository<Apolice, Integer>{
	
	public Apolice findByPlacaVeiculo(String placaVeiculo);
	
	public List<Apolice> findByCliente(Cliente cliente);

}
