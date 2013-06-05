package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import neo4j.N4JAdmin;

import social.User;

public class Connexion {
	
	/**
	 * Insertion d'un utilisateur (inscription)
	 * @param nom
	 * @param prenom
	 * @param sexe
	 * @param mail
	 * @param date
	 * @param mdp
	 * @return true si l'insertion s'est correctement realisee
	 */
	public static boolean insertUser(String nom, String prenom, String sexe, String mail, String date, String mdp){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		int nRows = 0;
		try {
			stmt = conn.createStatement();
			nRows = stmt.executeUpdate(
					"INSERT INTO User (nom,prenom,sexe,dateNaissance,mail,password) " +
					"VALUES ('"+nom+"','"+prenom+"','"+sexe+"','"+changeDate(date)+"','"+mail+"','"+mdp+"')");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		User user = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE idUser=(select max(idUser) from User)");
			while(rs.next()) {
				user = new User(rs.getInt("idUser"), rs.getString("sexe").charAt(0), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getDate("dateNaissance"), rs.getInt("profile"));
			}
			N4JAdmin admin = Bdd.connectNeo4j();
			admin.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (nRows != 0);
	}
	
	/**
	 * Connexion d'un utilisateur via le formulaire
	 * @param mail
	 * @param mdp
	 * @param session
	 * @return true si l'utilisateur s'est connecte
	 */
	public static boolean connectUser(String mail, String mdp, HttpSession session){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE mail='"+mail+"' AND password ='"+mdp+"'");
			while(rs.next()) {
				User user = new User(rs.getInt("idUser"), rs.getString("sexe").charAt(0), rs.getString("nom"), rs.getString("prenom"), mail, rs.getDate("dateNaissance"), mdp, rs.getInt("profile"));
				session.setAttribute("user", user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Connexion d'un utilisateur via le cookie
	 * @param id
	 * @param session
	 * @return true si l'utilisateur s'est connecte
	 */
	public static boolean connectUser(int id, HttpSession session){
		Connection conn = Bdd.connectBdd();
		Statement stmt;
		if(conn == null){
			return false;
		}
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE idUser='"+id+"'");
			while(rs.next()) {
				User user = new User(rs.getInt("idUser"), rs.getString("sexe").charAt(0), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getDate("dateNaissance"), rs.getString("password"), rs.getInt("profile"));
				session.setAttribute("user", user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Formatage de la date DD/MM/YYYY -> YYYY-MM-DD
	 * @param date
	 * @return String
	 */
	private static String changeDate(String date){
		String[] tab = date.split("/");
		return tab[2]+"-"+tab[1]+"-"+tab[0];
	}
}
