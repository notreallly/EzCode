package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection createConnection() {
		Connection con = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=projectDB";
		String user = "sa";
		String pass = "1234";

		try {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection object :" + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
