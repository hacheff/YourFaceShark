package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import social.User;

public class Recherche {

	public static List<User> selectRecherche(String s, int id, int debut){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return null;
		}
		Statement stmt;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			String requete = "SELECT u.idUser as id, nom, prenom, dateNaissance as date, sexe, mail, profile" +
					" FROM user u, amis a" +
					" WHERE u.idUser = a.idShark" +
					" AND ((nom like ('%" + s + "%') " +
					" OR prenom like ('%" + s + "%'))" +
					" )" +
					" LIMIT " + debut + " , " + (debut + 30) + "";
			rs = stmt.executeQuery(requete);

			while(rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("sexe").charAt(0), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getDate(rs.findColumn("date")), rs.getInt("profile"));
								
				list.add(user);
			}
			list.add(null);
			requete = "SELECT idUser as id, nom, prenom, dateNaissance as date, sexe, mail, profile" +
					" FROM user u" +
					" WHERE ((nom like ('%" + s + "%') " +
					" OR prenom like ('%" + s + "%'))" +
					" AND ( idUser " +
					" NOT IN (" +
					" SELECT idShark" +
					" FROM amis" +
					" WHERE idUser = " + id +
					" )))" +
					" AND idUser <> " + id +
					" LIMIT " + debut + " , " + (debut + 30) + "";
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
