package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
	
	public static Connection connectBdd(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://sql.free.fr/brain_fuck";
			conn = DriverManager.getConnection(url, "brain.fuck", "brainfuck11");
		} catch(SQLException e) {
			// S'il y a un problème durant la connexion
			System.out.println("********* NO");
			e.printStackTrace();
		} catch(Exception e) {
			// Si le driver JDBC ne peut pas être chargé
			e.printStackTrace();
			System.out.println("********* NO2");
		}
		return conn;
	}
	
}
