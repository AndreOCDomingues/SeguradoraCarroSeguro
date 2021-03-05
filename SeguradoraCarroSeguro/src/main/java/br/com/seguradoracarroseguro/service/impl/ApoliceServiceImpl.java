package br.com.seguradoracarroseguro.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.seguradoracarroseguro.model.Apolice;
import br.com.seguradoracarroseguro.model.ApoliceCompleta;
import br.com.seguradoracarroseguro.model.Cliente;
import br.com.seguradoracarroseguro.model.Vigencia;
import br.com.seguradoracarroseguro.repository.ApoliceRepository;
import br.com.seguradoracarroseguro.service.ApoliceService;
import br.com.seguradoracarroseguro.service.ClienteService;

@Service
public class ApoliceServiceImpl implements ApoliceService {

	@Autowired
	private ApoliceRepository apoliceRepository;

	@Autowired
	private ClienteService clienteService;

	@Override
	public Apolice criar(Apolice apolice) {
		if (!validaDadosApolice(apolice))
			throw new IllegalArgumentException("Necessário preencher todos os campos da apolice. " + apolice);

		if (!validaClienteCadastrado(apolice.getCliente().getId()))
			throw new IllegalArgumentException("Necessário associar um cliente a apolice. " + apolice);

		apolice.setNumero(getNovoNumberoApolice());
		return this.apoliceRepository.save(apolice);
	}

	@Override
	public List<Apolice> obterTodas() {
		return apoliceRepository.findAll();
	}

	@Override
	public Apolice obterPorNumero(Integer numero) {
		return this.apoliceRepository.findById(numero)
				.orElseThrow(() -> new IllegalArgumentException("Numero da apolice invalido"));
	}

	@Override
	public Apolice obterPorPlacaVeiculo(String placaVeiculo) {
		return this.apoliceRepository.findByPlacaVeiculo(placaVeiculo);
	}

	@Override
	public List<Apolice> obterPorCliente(String clienteId) {
		return this.apoliceRepository.findByCliente(clienteService.obterPorId(clienteId));
	}

	@Override
	public Apolice atualizar(Integer id, Apolice apolice) {
		if (null != id && null != apolice) {
			Apolice apoliceExistente = obterPorNumero(id);
			if (null != apoliceExistente) {
				return this.apoliceRepository.save(atualizaApolice(id, apolice, apoliceExistente));
			} else {
				return this.criar(apolice);
			}
		}
		return null;
	}

	@Override
	public void remover(Integer id) {
		this.apoliceRepository.deleteById(id);
	}

	private Apolice atualizaApolice(Integer idApolice, Apolice apolice, Apolice apoliceExistente) {
		Apolice apoliceAtualizada = new Apolice();
		//SETID da apolice - nao mudar
		apoliceAtualizada.setNumero(idApolice);
		//VIGENCIA
		if(null != apolice.getVigencia()) {
			Vigencia vigencia = new Vigencia();
			if(null != apolice.getVigencia().getInicio() 
					&& apolice.getVigencia().getInicio() != apoliceExistente.getVigencia().getInicio()) {
				vigencia.setInicio(apolice.getVigencia().getInicio());
			}else {				
				vigencia.setInicio(apoliceExistente.getVigencia().getInicio());
			}
			if(null != apolice.getVigencia().getFim() 
					&& apolice.getVigencia().getFim() != apoliceExistente.getVigencia().getFim()) {
				vigencia.setFim(apolice.getVigencia().getFim());
			}else {
				vigencia.setFim(apoliceExistente.getVigencia().getFim());
			}
			apoliceAtualizada.setVigencia(vigencia);
		}else {
			apoliceAtualizada.setVigencia(apoliceExistente.getVigencia());
		}
		//PLACA
		if(null != apolice.getPlacaVeiculo() && !apolice.getPlacaVeiculo().isEmpty() && !"".equals(apolice.getPlacaVeiculo())
				&& !apoliceExistente.getPlacaVeiculo().equals(apolice.getPlacaVeiculo())) {
			apoliceAtualizada.setPlacaVeiculo(apolice.getPlacaVeiculo());
		}else {
			apoliceAtualizada.setPlacaVeiculo(apoliceExistente.getPlacaVeiculo());
		}
		//VALOR
		if(null != apolice.getValor() && apoliceExistente.getValor().compareTo(apolice.getValor()) == 0) {
			apoliceAtualizada.setValor(apolice.getValor());
		}else {
			apoliceAtualizada.setValor(apoliceExistente.getValor());
		}
		//CLIENTE
		if(null != apolice.getCliente()) {
			Cliente cliente = new Cliente();
			if(null != apolice.getCliente().getId()  && apolice.getCliente().getId() != apoliceExistente.getCliente().getId()) {
				cliente.setId(apolice.getCliente().getId());
			}else {
				cliente.setId(apoliceExistente.getCliente().getId());
			}
			apoliceAtualizada.setCliente(cliente);
		}
		
		return apoliceAtualizada;
		
	}

	public boolean numeroApoliceExiste(Integer numero) {
		Optional<Apolice> apolice = this.apoliceRepository.findById(numero);
		if (null != apolice && apolice.isPresent())
			return true;
		return false;
	}

	private Integer getNovoNumberoApolice() {
		Integer novoNumeroApolice = 1;
		do {
			novoNumeroApolice = new Random().nextInt(Integer.MAX_VALUE);
		} while (numeroApoliceExiste(novoNumeroApolice));
		return novoNumeroApolice;
	}

	private boolean validaDadosApolice(Apolice apolice) {
		return null != apolice && null != apolice.getVigencia() && null != apolice.getVigencia().getInicio()
				&& null != apolice.getVigencia().getFim() && null != apolice.getPlacaVeiculo()
				&& null != apolice.getValor() && null != apolice.getCliente() && null != apolice.getCliente().getId();
	}

	private boolean validaClienteCadastrado(String id) {
		Cliente cliente = clienteService.obterPorId(id);
		if (null != cliente && null != cliente.getNome())
			return true;
		return false;
	}

	@Override
	public ApoliceCompleta consultaApoliceCompletaPorNumero(Integer numero) {
		return new ApoliceCompleta(this.obterPorNumero(numero));
	}

}
