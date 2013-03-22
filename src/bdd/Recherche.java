package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import social.User;

public class Recherche {

	public static List<User> selectRecherche(String s){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return null;
		}
		Statement stmt;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			String requete = "SELECT idUser as id, nom, prenom, date, sexe, mail, profile" +
					"FROM user" +
					"WHERE nom like ('%" + s + "%') " +
					"OR prenom like ('%" + s + "%')";
			rs = stmt.executeQuery(requete);

			while(rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("sexe").charAt(0), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getDate(rs.findColumn("date")), rs.getInt("profile"));
								
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<User>();
		}
		return list;
	}
	
}
