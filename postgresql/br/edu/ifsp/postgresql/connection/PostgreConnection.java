package br.edu.ifsp.postgresql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifsp.connection.AConnection;

public class PostgreConnection extends AConnection {

	/**
	 * Método que cria uma conexão com o banco de dados
	 * 
	 * @return uma conexão com o banco de dados
	 * @throws java.sql.SQLException
	 */
	public Connection connect() throws SQLException {

		try {

			Class.forName("org.postgresql.Driver");

			this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres",
					"1qaz2wsx");

		} catch (ClassNotFoundException ex) {

			throw new SQLException(ex.getMessage());

		}

		return this.connection;
	}

	public boolean executeUpdate(PreparedStatement preparedStatement) throws SQLException {

		if (!this.connection.isClosed() && this.connection != null) {

			return preparedStatement.executeUpdate() > 0;

		}

		return false;
	}
}
