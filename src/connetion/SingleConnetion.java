package connetion;
/**
 * Responsavel por fazer a conexão com banco de dados;

 * @author vagne
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnetion {

	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;

	static {
		conectar();
	}

	public SingleConnetion() {
		conectar();
	}

	private static void conectar() {
		try {

			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
			}

		} catch (Exception e) {
			throw new RuntimeException("erro ao conectar ao banco");
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
