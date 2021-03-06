package com.joice.sistemaIrrigamento.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{
	private ArrayList dados = null;
	private String[] colunas = null;
	
	public ModeloTabela(ArrayList dad, String[] col){
		setDados(dad);
		setColunas(col);		
	}
	
	public ArrayList getDados(){
		return dados;
	}
	
	public void setDados(ArrayList dados){
		this.dados = dados;
	}
	
	public String[] getColunas(){
		return colunas;
	}
	
	public void setColunas(String [] colunas){
		this.colunas = colunas;
	}
	
	public int getColumnCount(){
		return colunas.length;
	}
	
	public int getRowCount(){
		return dados.size();
	}
	
	public String getColumnName(int numCol){
		return colunas[numCol];
	}
	
	public Object getValueAt(int numDados, int numCol){
		Object[] dados = (Object[])getDados().get(numDados);
		return dados[numCol];
	}
}
