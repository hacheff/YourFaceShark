package bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {
	
	public static boolean insertUser(String nom, String prenom, String sexe, String mail, String date, String mdp){
		Connection conn = Bdd.connectBdd();
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
	
	private static String changeDate(String date){
		String[] tab = date.split("/");
		return tab[2]+"-"+tab[1]+"-"+tab[0];
	}
}
