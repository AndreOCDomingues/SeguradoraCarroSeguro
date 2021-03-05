package br.com.seguradoracarroseguro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.seguradoracarroseguro.model.ApoliceCompleta;
import br.com.seguradoracarroseguro.service.ApoliceService;

@RestController
@RequestMapping("/consultaapolices")
public class ConsultaApoliceController {

	@Autowired
	private ApoliceService apoliceService;


	@RequestMapping(value = "/apolicecompleta", params = "numero", method = RequestMethod.GET)
	public ApoliceCompleta consultaApoliceCompletaPorNumero(@RequestParam("numero") Integer numero) {
		return this.apoliceService.consultaApoliceCompletaPorNumero(numero);
	}

}
