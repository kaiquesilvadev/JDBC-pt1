package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program2 {

	public static void main(String[] args) {

		//inserir nova dado no banco 

		Connection conn2 = null;
		PreparedStatement st2 = null;
		
		try {
			conn2 = DB.getConnection();

			st2 = conn2.prepareStatement( //permite a criação de instruções SQL parametrizadas. 
					"UPDATE seller " + "SET BaseSalary = BaseSalary + ? " 
					+ "WHERE " 
					+ "(DepartmentId = ?)"); // ?definir valores para esses placeholders de forma segura

			st2.setDouble(1, 200.0);
			st2.setInt(2, 2);

			int rowsAffected = st2.executeUpdate();

			System.out.println("Done! Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st2);
			DB.closeConnection();
		}
	}

}
