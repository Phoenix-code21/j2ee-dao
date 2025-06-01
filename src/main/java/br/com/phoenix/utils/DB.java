package br.com.phoenix.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

	private static String url = "jdbc:mysql://localhost/phoenix";
	private static String user = "root";
	private static String pass = "";
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static Connection conn;

	private DB() {
		// setando driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException err) {
			System.out.println("001 - Erro ao realizar conexão com banco de dados");
		}

		// conexão com banco de dados
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(url, user, pass);
			}
		} catch (SQLException err) {
			System.out.println("002 - Erro ao realizar conexão com banco de dados");
		}
	}

	public static Connection getConnection() {
		new DB();
		return conn;
	}

}
