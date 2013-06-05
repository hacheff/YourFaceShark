package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import neo4j.GraphDB;
import neo4j.N4JAdmin;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

public class Bdd {
	
	private static N4JAdmin adminStatic = null;
	
	/**
	 * Retourne l'objet d'acces a la base de donnees MYSQL
	 * @return Connection
	 */
	public static Connection connectBdd(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/yourfaceshark";
			conn = DriverManager.getConnection(url, "root", "");
		} catch(SQLException e) {
			// S'il y a un probleme durant la connexion
			System.out.println("********* PROBLEME DURANT LA CONNEXION");
			e.printStackTrace();
		} catch(Exception e) {
			// Si le driver JDBC ne peut pas etre charge
			e.printStackTrace();
			System.out.println("********* PROBLEME DRIVER JDBC");
		}
		return conn;
	}
	
	/**
	 * Retourne l'objet d'acces a la base de donnees NEO4J
	 * @return N4JAdmin
	 */
	public static N4JAdmin connectNeo4j(){
		if(adminStatic == null){
			Node root;
			adminStatic = new N4JAdmin();
		
			Transaction tx = GraphDB.getGraphDb().beginTx();
			try {
				root = GraphDB.getGraphDb().createNode();
				root.setProperty("Nom", "yourfaceshark");
				tx.success();
			} finally {
				tx.finish();
			}
		}
		return adminStatic;
	}
	
}
