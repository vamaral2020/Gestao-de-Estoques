package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeansCursoJsp;
import connetion.SingleConnetion;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnetion.getConnection();
	}

	public void salvar(BeansCursoJsp usuario) {

		try {
			String sql = "insert into usuario(login, senha, nome, fone) values(?,?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getFone());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public List<BeansCursoJsp> listar() throws Exception {
		List<BeansCursoJsp> listar = new ArrayList<BeansCursoJsp>();

		String sql = "select * from usuario";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeansCursoJsp beansCursoJsp = new BeansCursoJsp();
			beansCursoJsp.setId(resultSet.getLong("id"));
			beansCursoJsp.setLogin(resultSet.getString("login"));
			beansCursoJsp.setSenha(resultSet.getString("senha"));
			beansCursoJsp.setNome(resultSet.getString("nome"));
			beansCursoJsp.setFone(resultSet.getString("fone"));

			listar.add(beansCursoJsp);

		}

		return listar;

	}
	
	

	public void delete(String id) {

		try {
			String sql = "delete from usuario where id = '" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	}
	
	

	public BeansCursoJsp consultar(String id) throws Exception {
		String sql = "select * from usuario where id='" + id + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			BeansCursoJsp beansCursoJsp = new BeansCursoJsp();
			beansCursoJsp.setId(resultSet.getLong("id"));
			beansCursoJsp.setLogin(resultSet.getString("login"));
			beansCursoJsp.setSenha(resultSet.getString("senha"));
			beansCursoJsp.setNome(resultSet.getString("nome"));
			beansCursoJsp.setFone(resultSet.getString("fone"));
			
			return beansCursoJsp;
		}
		
		return null;
	}
	
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as qtde from usuario where login='" + login + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			
			return resultSet.getInt("qtde")<=0;//return true;
		}
		
		return false;
	}
	
	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "select count(1) as qtde from usuario where login='" + login + "'and id <>" +id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			
			return resultSet.getInt("qtde")<=0;//return true;
		}
		
		return false;
	}
	
	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtde from usuario where senha='" + senha +"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			
			return resultSet.getInt("qtde")<=0;//return true;
		}
		
		return false;
	}
	
	//continuar aqui!
	public void atualizar(BeansCursoJsp usuario) {
		
		try {
		String sql = "update usuario set login = ?, senha = ?, nome = ?, fone = ? where id = " + usuario.getId();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, usuario.getLogin());
		preparedStatement.setString(2, usuario.getSenha());
		preparedStatement.setString(3, usuario.getNome());
		preparedStatement.setString(4, usuario.getFone());
		preparedStatement.executeUpdate();
		connection.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
