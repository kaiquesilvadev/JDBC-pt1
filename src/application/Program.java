package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		// consulta no banco de dado

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();

			st = conn.createStatement();// executar consultas SQL no banco de dados.

			rs = st.executeQuery("select * from department");// aqui manda um comando para o SQl

			while (rs.next()) { // next percorre a tabela e retorna falo na ultima linha
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
}