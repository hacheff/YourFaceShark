package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Like {
	/**
	 * Ajout d'un like sur un POST
	 * @param id_post
	 * @param id_likeur
	 * @param choix
	 * @return true si l'insertion s'est correctement deroulee
	 */
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
	
	/**
	 * Nombre de like / unlike sur un POST
	 * @param idPost
	 * @param idUser
	 * @return int[]
	 */
	public static int[] requeteLike(int idPost, int idUser){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return null;
		}
		Statement stmt;
		ResultSet rs;
		int[] tab= new int[4];
		try {
			stmt = conn.createStatement();
			String requete = "SELECT (SELECT count(*) FROM KOULE k WHERE k.idPost = "+ idPost +" AND choix = 1) as nblike," +
					" (SELECT count(*) FROM KOULE k WHERE k.idPost = "+ idPost +" AND choix = 0) as nbunlike," +
					" (SELECT count(*) FROM KOULE k WHERE k.idPost = "+ idPost +" AND choix = 1 AND k.idLikeur = "+ idUser +") as likeuser," +
					" (SELECT count(*) FROM KOULE k WHERE k.idPost = "+ idPost +" AND choix = 0 AND k.idLikeur = "+ idUser +") as unlikeuser" +
					" FROM POST p";
			rs = stmt.executeQuery(requete);
			while(rs.next()) {
				tab[0]=rs.getInt("nblike");
				tab[1]=rs.getInt("nbunlike");
				tab[2]=rs.getInt("likeuser");
				tab[3]=rs.getInt("unlikeuser");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return tab;
	}
}
