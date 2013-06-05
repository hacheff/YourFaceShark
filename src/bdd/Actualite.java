package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Actualite {
	
	/**
	 * Retourne le resultat pour le fil d'actualite
	 * Les posts des amis ainsi que ceux de l'utilisateur connecte
	 * @param idUser
	 * @param debut
	 * @return ResultSet
	 */
	public static ResultSet selectActu(Integer idUser, int debut){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return null;
		}
		Statement stmt;
		ResultSet nRows = null;
		try {
			stmt = conn.createStatement();
			String requete = "SELECT u.idUser as 'id', p.idPost as 'idPost', u.nom as 'nom', u.prenom as 'prenom', p.texte as 'text', p.date as 'date', p.url as 'url'," +
					" (SELECT count(*) FROM COMMENTAIRES c WHERE c.idPost =p.idPost) as nbCom," +
					" (SELECT count(*) FROM KOULE k WHERE k.idPost =p.idPost AND choix = 1) as nblike," +
					" (SELECT count(*) FROM KOULE k WHERE k.idPost =p.idPost AND choix = 0) as nbunlike," +
					" (SELECT count(*) FROM KOULE k WHERE k.idPost =p.idPost AND choix = 1 AND k.idLikeur = "+ idUser +") as likeuser," +
					" (SELECT count(*) FROM KOULE k WHERE k.idPost =p.idPost AND choix = 0 AND k.idLikeur = "+ idUser +") as unlikeuser" +
					" FROM POST p, USER u" +
					" WHERE p.idPosteur = u.idUser" +
					" AND ( idPosteur " +
					" IN (" +
					" SELECT idShark" +
					" FROM amis" +
					" WHERE idUser = " + idUser +
					" ) OR idPosteur = " + idUser +
					" ) ORDER BY date DESC" +
					" LIMIT " + debut + " , " + (debut + 30) + "";
			nRows = stmt.executeQuery(requete);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return nRows;
	}
	
}
