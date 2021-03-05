package br.com.seguradoracarroseguro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.seguradoracarroseguro.model.Cliente;
import br.com.seguradoracarroseguro.repository.ClienteRepository;
import br.com.seguradoracarroseguro.service.ClienteService;
import br.com.seguradoracarroseguro.util.ValidaCpf;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente criar(Cliente cliente) {
		if (!validaDadosCliente(cliente))
			throw new IllegalArgumentException("Necessário preencher todos os campos do cliente. " + cliente);

		if (!ValidaCpf.isCPF(cliente.getCpf()))
			throw new IllegalArgumentException("CPF inválido! " + cliente.getCpf());

		Cliente clienteExistente = obterPorCpf(cliente.getCpf());
		if (null != clienteExistente)
			throw new IllegalArgumentException(
					"Cliente já existe! ID Cliente: " + clienteExistente.getId() + " " + clienteExistente);

		cliente.setId(null);
		return this.clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> obterTodos() {
		return this.clienteRepository.findAll();
	}

	@Override
	public Cliente obterPorId(String id) {
		return this.clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Funcionário não existe."));
	}

	@Override
	public Cliente obterPorNome(String nome) {
		return this.clienteRepository.findByNome(nome);
	}

	@Override
	public Cliente obterPorCpf(String cpf) {
		return this.clienteRepository.findByCpf(cpf);
	}

	@Override
	public Cliente atualizar(String id, Cliente cliente) {
		if (null != cliente) {
			if (null != cliente.getCpf()) {
				Cliente clienteExistente = obterPorId(id);
				if (null != clienteExistente) {
					return clienteRepository.save(atualizaCliente(cliente, clienteExistente));
				} else {
					return this.criar(cliente);
				}
			}
		}
		return null;
	}

	private Cliente atualizaCliente(Cliente cliente, Cliente clienteExistente) {
		Cliente clienteAtualizado = new Cliente();
		// SETID do cliente consultado no BD
		clienteAtualizado.setId(clienteExistente.getId());
		// NOME
		if (null != cliente.getNome() && !cliente.getNome().isEmpty() && !"".equals(cliente.getNome())
				&& !clienteExistente.getNome().equals(cliente.getNome())) {
			clienteAtualizado.setNome(cliente.getNome());
		} else {
			clienteAtualizado.setNome(clienteExistente.getNome());
		}
		// CPF
		if (null != cliente.getCpf() && !cliente.getCpf().isEmpty() && !"".equals(cliente.getCpf())
				&& !clienteExistente.getCpf().equals(cliente.getCpf())) {
			clienteAtualizado.setCpf(cliente.getCpf());
		} else {
			clienteAtualizado.setCpf(clienteExistente.getCpf());
		}
		// CIDADE
		if (null != cliente.getCidade() && !cliente.getCidade().isEmpty() && !"".equals(cliente.getCidade())
				&& !clienteExistente.getCidade().equals(cliente.getCidade())) {
			clienteAtualizado.setCidade(cliente.getCidade());
		} else {
			clienteAtualizado.setCidade(clienteExistente.getCidade());
		}
		// UF
		if (null != cliente.getUf() && !cliente.getUf().isEmpty() && !"".equals(cliente.getUf())
				&& !clienteExistente.getUf().equals(cliente.getUf())) {
			clienteAtualizado.setUf(cliente.getUf());
		} else {
			clienteAtualizado.setUf(clienteExistente.getUf());
		}

		return clienteAtualizado;
	}

	@Override
	public boolean remover(String id) {
		try {
			clienteRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	private boolean validaDadosCliente(Cliente cliente) {
		return null != cliente && null != cliente.getNome() && null != cliente.getCpf() && null != cliente.getCidade()
				&& null != cliente.getUf();
	}

}
