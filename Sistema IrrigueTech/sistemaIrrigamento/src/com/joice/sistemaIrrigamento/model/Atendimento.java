package com.joice.sistemaIrrigamento.model;

/**
 * 
 * @author Joice Marques
 * @since 10/04/2017
 * @category model
 * 
 */

public class Atendimento {
	private String dataAtendimento;
	private Cliente cliente;

	public Atendimento() {
		// construtor sem parametros;
	}

	// construtor com parametros
	public Atendimento(String dataAtendimento, Cliente cliente) {
		this.dataAtendimento = dataAtendimento;
		this.cliente = cliente;
	}

}
