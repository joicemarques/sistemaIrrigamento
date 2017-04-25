package com.joice.sistemaIrrigamento.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Joice Marques
 * @since 10/04/2017
 * @category model
 * 
 */

public class Cliente {
	private String nomeCliente;
	private String documento_cliente;
	private int quantidade_mangueiras;
	private String pesquisa;

	ConnectionModel conn = new ConnectionModel();
	PreparedStatement pst = null;
	ResultSet rs = null;

	public Cliente() {
		// metodo construtor sem parametros
	}

	public Cliente(String nomeCliente, String documento_cliente, int quantidade_mangueiras) {
		this.nomeCliente = nomeCliente;
		this.documento_cliente = documento_cliente;
		this.quantidade_mangueiras = quantidade_mangueiras;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDocumento_cliente() {
		return documento_cliente;
	}

	public void setDocumento_cliente(String documento_cliente) {
		this.documento_cliente = documento_cliente;
	}

	public int getQuantidade_mangueiras() {
		return quantidade_mangueiras;
	}

	public void setQuantidade_mangueiras(int quantidade_mangueiras) {
		this.quantidade_mangueiras = quantidade_mangueiras;
	}

	// metodo que busca um cliente a partir de uma query
	public Cliente buscaCliente(Cliente cliente) throws SQLException {
		String sql = "Select * from cliente where nomecliente like '%" + getPesquisa() + "%'";

		pst = conn.getConexao().prepareStatement(sql);
		rs = pst.executeQuery();

		try {
			while (rs.next()) {
				setNomeCliente(rs.getString("nomecliente"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return cliente;
	}
}
