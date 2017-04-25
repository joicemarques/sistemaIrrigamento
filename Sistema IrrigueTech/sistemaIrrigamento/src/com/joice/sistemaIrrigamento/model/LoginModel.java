package com.joice.sistemaIrrigamento.model;

/**
 * 
 * @author Joice Marques
 * @since 10/04/2017
 * @category model
 * 
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
	ConnectionModel conn = new ConnectionModel();
	PreparedStatement pst;
	  //valida o login que o usuario informou
	 public boolean validarLogin(String usuario, String senha) throws ClassNotFoundException {		 
		 try {
	         pst = conn.getConexao().prepareStatement("SELECT * FROM atendente WHERE usuario=? and senha=?");
	         pst.setString(1, usuario);
	         pst.setString(2, senha);
	         ResultSet rs = pst.executeQuery();
	         if (rs.next()) { 
	        	 conn.getConexao().close();
	        	 return true; // nessa caso a query retornou pelo menos uma linha que contem os dados inseridos	        	 
	         } else {
	        	 conn.getConexao().close();
	             return false; // nesse caso a query nao retornou nenhuma linha, logo os valores sao invalidos
	         }}catch (SQLException e) {
	        	//retorna os erros que ocorrerem
	        	System.out.println("Excecao".concat(e.getMessage())); 	        	
	            return false;
	        }		
	 }
}
