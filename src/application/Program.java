package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null; 
		
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false); //nao confirmar automaticamente 
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			//int x = 1;
			//if(x < 2) {
				//throw new SQLException("Fake error");
			//}
						
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit(); //se nao der erro ate aqui, pode confirmar
			
			System.out.println("rows1 " + rows1);
			
			System.out.println("rows2 " + rows2);
			
		}
		catch(SQLException e){
			try {
				conn.rollback();
				throw new DbException("transaction rolledback! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e.getMessage());
			}
		}
		finally {
			DB.closeSt(st);
			DB.closeConnection();
		}
	}
}
