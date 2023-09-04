package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program5 {
	/*
	 * Transações são operações em um banco de dados que envolvem uma série de
	 * ações, como consultas (queries) e atualizações, que são tratadas como uma
	 * única unidade de trabalho. As transações são usadas para garantir a
	 * consistência e a integridade dos dados em um banco de dados relacional.
	 * Seguem o conceito ACID, que é um acrônimo para Atomicidade, Consistência,
	 * Isolamento e Durabilidade:
	 */

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();

			// Ele desativa o modo de confirmação automática para a conexão com o banco de
			// dados.
			conn.setAutoCommit(false);

			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

			// int x = 1;
			// if (x < 2) {
			// throw new SQLException("Fake error");
			// }

			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

			// salva todas as alterações feitas na transação atual no banco de dados
			// permanentemente.
			conn.commit();

			System.out.println("rows1 = " + rows1);
			System.out.println("rows2 = " + rows2);
		} catch (SQLException e) {
			try {

				/*
				 * Se algo der errado em uma transação ou se você quiser descartar as alterações
				 * feitas durante a transação, você pode usar o rollback()
				 */
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}