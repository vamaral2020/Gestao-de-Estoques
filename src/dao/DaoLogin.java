package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connetion.SingleConnetion;

public class DaoLogin {
	
	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnetion.getConnection();
	}

	
	public boolean validarLogin(String login, String senha) throws Exception{
		
		String sql = "select * from usuario where login = '"+login+"'and senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			return true;//possui usuario cadastrado
		}else {
			return false;//não possui usuario cadastrado;
		}
	}
	
	

}
