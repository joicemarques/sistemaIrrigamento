package com.joice.sistemaIrrigamento.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author Joice Marques
 * @since 10/04/2017
 * @category model
 * 
 */

import java.util.ArrayList;
import java.util.List;

import com.joice.sistemaIrrigamento.view.AtendimentoView;

public class Chamado {
	private int dataChamado;
	private Cliente cliente;
	List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	
	public Chamado(int dataChamado, Cliente cliente, List<Atendimento> atendimentos) {
		this.dataChamado = dataChamado;
		this.cliente = cliente;
		this.atendimentos = atendimentos;
	}

}
