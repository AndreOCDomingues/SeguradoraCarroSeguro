package br.com.seguradoracarroseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.seguradoracarroseguro.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

	public Cliente findByCpf(String cpf);

	public Cliente findByNome(String nome);


}
