package bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Like {
	public static boolean insertLike(int id_post, int id_likeur, int choix){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		int nRows = 0;
		try {
			stmt = conn.createStatement();
			String requete = "INSERT INTO koule (idPost,idLikeur,choix) " + "VALUES ('"+id_post+"','"+id_likeur+"','"+choix+"')";
			nRows = stmt.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return (nRows != 0);
	}
}
