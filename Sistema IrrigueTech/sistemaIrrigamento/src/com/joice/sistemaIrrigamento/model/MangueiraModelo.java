package com.joice.sistemaIrrigamento.model;
/**
 * 
 * @author Joice Marques
 * @since 10/04/2017
 * @category model
 * 
 */
public class MangueiraModelo {
	int quantidade_bicos;
	boolean status_mangueira;
	int id_cliente;
	
	public MangueiraModelo(int quantidade_bicos, boolean status_mangueira, int id_cliente) {
		this.quantidade_bicos = quantidade_bicos;
		this.status_mangueira = status_mangueira;
		this.id_cliente = id_cliente;
	}

	public int getQuantidade_bicos() {
		return quantidade_bicos;
	}

	public void setQuantidade_bicos(int quantidade_bicos) {
		this.quantidade_bicos = quantidade_bicos;
	}

	public boolean isStatus_mangueira() {
		return status_mangueira;
	}

	public void setStatus_mangueira(boolean status_mangueira) {
		this.status_mangueira = status_mangueira;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	
	
	
	
}
