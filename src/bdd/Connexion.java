package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import social.User;

public class Connexion {
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return (nRows != 0);
	}
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
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
	
	private static String changeDate(String date){
		String[] tab = date.split("/");
		return tab[2]+"-"+tab[1]+"-"+tab[0];
	}
}
