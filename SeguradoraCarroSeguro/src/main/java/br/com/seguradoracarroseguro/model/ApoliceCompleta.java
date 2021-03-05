package br.com.seguradoracarroseguro.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ApoliceCompleta{
	
	private Apolice apolice;
	
	public ApoliceCompleta() {

	}
	
	public ApoliceCompleta(Apolice apolice) {
		this.apolice = apolice;
	}
	
	public Apolice getApolice() {
		return apolice;
	}

	public void setApolice(Apolice apolice) {
		this.apolice = apolice;
	}
	
	@Override
	public String toString() {
		return "Numero: "+this.apolice.getNumero()+" Vigencia: de "+this.apolice.getVigencia().getInicio()+ " até "+this.apolice.getVigencia().getFim()+" Placa Veiculo: "+this.apolice.getPlacaVeiculo();
	}
	
	public Boolean getApoliceVencida() {
		return this.apolice.getVigencia().getFim().before(new Date());
	}
	
	public Integer getDiasParaVencer() {
		long diferencaDias = (this.apolice.getVigencia().getFim().getTime() -this.apolice.getVigencia().getInicio().getTime()) / (1000*60*60*24);
		return getApoliceVencida() ? 0 : new Integer((int) diferencaDias);
	}
	public Integer getDiasVencidos() {
		long diferencaDias = (new Date().getTime() - this.apolice.getVigencia().getFim().getTime()) / (1000*60*60*24);
		return getApoliceVencida() ? new Integer((int) diferencaDias) : 0;
	}

	
	

}
