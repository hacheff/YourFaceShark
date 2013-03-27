package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
	
	public static Connection connectBdd(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/yourfaceshark";
			conn = DriverManager.getConnection(url, "root", "");
		} catch(SQLException e) {
			// S'il y a un problème durant la connexion
			System.out.println("********* PROBLEME DURANT LA CONNEXION");
			e.printStackTrace();
		} catch(Exception e) {
			// Si le driver JDBC ne peut pas être chargé
			e.printStackTrace();
			System.out.println("********* PROBLEME DRIVER JDBC");
		}
		return conn;
	}
	
}
