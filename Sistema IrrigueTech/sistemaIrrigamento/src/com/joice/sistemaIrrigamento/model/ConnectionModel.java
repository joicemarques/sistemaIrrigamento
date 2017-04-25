package com.joice.sistemaIrrigamento.model;

/**
 * 
 * @author Joice Marques
 * @since 10/04/2017
 * @category model
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionModel {
	public ResultSet rs; //armazenar o resultado de uma query
	public static String conexao;
		
	public Connection getConexao() throws SQLException{
		try{
		 Class.forName("org.postgresql.Driver");
		 conexao = "jdbc:postgresql://localhost:5432/projeto?user=postgres&password=postgres";
        } catch (ClassNotFoundException e) {
            System.err.println("Excecao: " + e.toString());
        }
		return DriverManager.getConnection(conexao);
    } 
	
	
   
}
