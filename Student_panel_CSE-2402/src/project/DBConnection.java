package project;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DBConnection {
	
	private static String jdbc_driver = "com.mysql.jdbc.Driver";
	private static String db_url = "jdbc:mysql://localhost/javaProject";
	private static String user = "root";
	private static String pass = "";
	
	public static Connection ConnecrDb() {
		try {
			Class.forName(jdbc_driver);
			Connection conn = DriverManager.getConnection(db_url, user, pass);
			System.out.println("Connected");
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}

}