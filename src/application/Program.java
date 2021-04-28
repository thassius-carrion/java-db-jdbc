package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from department"); //comando SQL, o resultado da consulta por st, estou adicionando no resultSet
			
			while(rs.next()){
				System.out.println(rs.getInt("Id") + ", " + rs.getString("name")); //nesta row do resultSet, queremos o int da coluna Id e o String da coluna name
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeRs(rs);
			DB.closeSt(st);
			DB.closeConnection();
		}

	}
}
