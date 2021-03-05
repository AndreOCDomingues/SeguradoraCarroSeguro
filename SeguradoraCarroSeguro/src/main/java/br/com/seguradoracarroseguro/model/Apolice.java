package br.com.seguradoracarroseguro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Apolice {

	@Id
	private Integer numero;
	private Vigencia vigencia;
	private String placaVeiculo;
	private Double valor;

	@DBRef
	private Cliente cliente;	
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vigencia getVigencia() {
		return vigencia;
	}
	public void setVigencia(Vigencia vigencia) {
		this.vigencia = vigencia;
	}	
	
	@Override
	public String toString() {
		return "Numero: "+this.numero+" Vigencia: de "+this.vigencia.getInicio()+ " até "+this.vigencia.getFim()+" Placa Veiculo: "+this.placaVeiculo;
	}
	
}
