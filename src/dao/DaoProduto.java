package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Produtos;
import connetion.SingleConnetion;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnetion.getConnection();
	}

	public void salvarProduto(Produtos produtos) {

		try {
			String sql = "insert into produtos(nome, qtde, valor) values(?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produtos.getNome());
			insert.setInt(2, produtos.getQtde());
			insert.setDouble(3, produtos.getValor());
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

	public List<Produtos> listarProdutos() throws SQLException {
		List<Produtos> listarProdutos = new ArrayList<Produtos>();

		String sql = "select * from produtos";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Produtos produtos = new Produtos();

			produtos.setId(resultSet.getLong("id"));
			produtos.setNome(resultSet.getString("nome"));
			produtos.setQtde(resultSet.getInt("qtde"));
			produtos.setValor(resultSet.getDouble("valor"));

			listarProdutos.add(produtos);
		}

		return listarProdutos;

	}

	public void delete(String id) {

		try {

			String sql = "delete from produtos where id= '" + id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
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

	public Produtos consultar(String id) throws SQLException {

		String sql = "select * from produtos where id = '" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			Produtos produtos = new Produtos();
			produtos.setId(resultSet.getLong("id"));
			produtos.setNome(resultSet.getString("nome"));
			produtos.setQtde(resultSet.getInt("qtde"));
			produtos.setValor(resultSet.getDouble("valor"));

			return produtos;
		}
		return null;
	}

	public boolean validarProduto(String nome) throws SQLException {
		String sql = "select count(1) as qtde from produtos where nome = '" + nome + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtde") <= 0;
		}
		return false;
	}

	public boolean validarProdutoUpdate(String nome, String id) throws SQLException {

		String sql = "select count(1) as qtde from produtos where nome='" + nome + "' and id<>'" + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("qtde") <= 0;
		}
		return false;

	}

	public void atualizarProduto(Produtos produto) {

		try {
			String sql = "update produtos set nome = ?, qtde=?, valor =? where id =" + produto.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setInt(2, produto.getQtde());
			statement.setDouble(3, produto.getValor());
			statement.executeUpdate();
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

}
