package bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Profil {
	public static boolean modifyUser(int id, String nom, String prenom, String sexe, String mail, String date, String mdp){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		int nRows = 0;
		try {
			stmt = conn.createStatement();
			nRows = stmt.executeUpdate("UPDATE User SET sexe='"+ sexe +"', nom='"+ nom +"', prenom='"+ prenom +"', mail='"+ mail +"', dateNaissance='"+ changeDate(date) +"', password='"+ mdp +"' WHERE idUser='" + id + "'");
			System.out.println("UPDATE User SET sexe='"+ sexe +"', nom='"+ nom +"', prenom='"+ prenom +"', mail='"+ mail +"', dateNaissance='"+ changeDate(date) +"', password='"+ mdp +"' WHERE idUser='" + id + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return (nRows != 0);
	}
	
	public static String changeDate(String date){
		String[] tab = date.split("/");
		return tab[2]+"-"+tab[1]+"-"+tab[0];
	}
	
	public static String reverseDate(String date){
		String[] tab = date.split("-");
		return tab[2]+"/"+tab[1]+"/"+tab[0];
	}
}
