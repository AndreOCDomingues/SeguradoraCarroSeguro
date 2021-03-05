package br.com.seguradoracarroseguro.model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Cliente {

	@Id
	private String id;
	private String nome;
	private String cpf;
	private String cidade;
	private String uf;		
	
	public String getId() {
		return id;
	}
	public void setId(String  id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Override
	public String toString() {
		return "nome: "+this.nome+" cpf: "+this.cpf+" cidade: "+this.cidade+" uf: "+this.uf;
	}
	
}
