package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import social.Commentaire;
import social.Post;
import social.User;

public class Profil {
	
	/**
	 * Retourne l'utilisateur qui a pour id celui passe en parametre
	 * @param id
	 * @return
	 */
	public static User getUserById(int id){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return null;
		}
		Statement stmt;
		User user = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE idUser='"+id+"'");
			while(rs.next()) {
				user = new User(rs.getInt("idUser"), rs.getString("sexe").charAt(0), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getDate("dateNaissance"), rs.getInt("profile"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	/**
	 * Modification de l'utilisateur...
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param sexe
	 * @param mail
	 * @param date
	 * @param mdp
	 * @return
	 */
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return (nRows != 0);
	}
	
	/**
	 * Modification de la photo de profil de l'utilisateur qui a pour id celui passe en parametre
	 * @param id
	 */
	public static void setPhotoProfil(int id){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return;
		}
		Statement stmt;
		try{
			stmt = conn.createStatement();
			String rqt = "UPDATE user SET profile=(SELECT MAX(idPhoto) FROM photos) WHERE idUser ='"+ id +"'";
			stmt.executeUpdate(rqt);
		}catch(SQLException e){
			e.printStackTrace();
		}
	} 
	
	/**
	 * Retourne tous les POST de l'utilisateur qui a pour id celui passe en parametre
	 * @param idUser
	 * @param debut
	 * @return
	 */
	public static List<Post> selectPost(Integer idUser, int debut){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return null;
		}
		Statement stmt;
		ResultSet rs = null;
		List<Post> list = new ArrayList<Post>();
		try {
			stmt = conn.createStatement();
			String requete = "SELECT p.idPost as 'id', p.texte as 'text', p.date as 'date', p.url as 'url'," +
					" (SELECT count(*) FROM COMMENTAIRES c WHERE c.idPost = p.idPost) as nbCom" +
					" FROM POST p" +
					" WHERE idPosteur = '" + idUser + "'" +					
					" ORDER BY date DESC" +
					" LIMIT " + debut + " , " + (debut + 30) + "";

			
			rs = stmt.executeQuery(requete);
			
			while(rs.next()) {
				Post post = new Post();
				post.setIdPost(rs.getInt("id"));
				post.setIdPosteur(idUser);
				post.setDate(rs.getDate(rs.findColumn("date")));
				post.setTexte(rs.getString("text"));
				post.setUrl(rs.getString("url"));
				post.setNbCom(rs.getInt("nbCom"));
				
				list.add(post);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Post>();
		}
		return list;
	}
	
	/**
	 * Insertion d'un POST...
	 * @param idPosteur
	 * @param idCible
	 * @param texte
	 * @param url
	 * @return
	 */
	public static boolean insertPost(int idPosteur, int idCible, String texte, String url){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		int nRows = 0;
		try {
			stmt = conn.createStatement();
			nRows = stmt.executeUpdate(
					"INSERT INTO Post (idPosteur,idCible,texte,url) " +
					"VALUES ('"+idPosteur+"','"+idCible+"','"+texte+"','"+url+"')");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return (nRows != 0);
	}
	
	/**
	 * Insertion d'un COMMENTAIRE
	 * @param idPost
	 * @param idUser
	 * @param commentaire
	 * @return
	 */
	public static boolean insertComentaire(int idPost, int idUser, String commentaire){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		int nRows = 0;
		try {
			stmt = conn.createStatement();
			nRows = stmt.executeUpdate(
					"INSERT INTO Commentaires (idPost,idUser,commentaire) " +
					"VALUES ('"+idPost+"','"+idUser+"','"+commentaire+"')");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return (nRows != 0);
	}
	
	/**
	 * Retourne la liste des COMMENTAIRE qui appartienne au POST qui a pour id celui passe en parametre
	 * @param idPost
	 * @param debut
	 * @return
	 */
	public static List<Commentaire> selectCommentairesByIdPost(Integer idPost, int debut){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return null;
		}
		Statement stmt;
		ResultSet rs = null;
		List<Commentaire> list = new ArrayList<Commentaire>();
		try {
			stmt = conn.createStatement();
			String requete = "SELECT u.idUser as 'id', nom, prenom, dateNaissance, sexe, mail, profile, c.idPost as 'idPost', c.commentaire as 'commentaire'" +
					" FROM COMMENTAIRES c, USER u" +
					" WHERE c.idUser = u.idUser" +	
					" AND c.idPost = " + idPost +
					" ORDER BY c.idCommentaire" +
					" LIMIT " + debut + " , " + (debut + 30) + "";

			rs = stmt.executeQuery(requete);
			
			while(rs.next()) {
				Commentaire commentaire = new Commentaire();
				//User(int id, char sexe, String nom, String prenom, String mail, Date ddn, int idPhoto)
				User user = new User(rs.getInt("id"), rs.getString("sexe").charAt(0), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getDate("dateNaissance"), rs.getInt("profile"));
				
				commentaire.setIdPost(rs.getInt("idPost"));
				commentaire.setCommentaire(rs.getString("commentaire"));
				commentaire.setUser(user);
				
				list.add(commentaire);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Commentaire>();
		}
		return list;
	}
	
	/**
	 * Retourne la liste des amis
	 * @param id
	 * @param debut
	 * @return
	 */
	public static List<User> selectShark(int id, int debut){
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
					" LIMIT " + debut + " , " + (debut + 30) + "";
			rs = stmt.executeQuery(requete);

			while(rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("sexe").charAt(0), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getDate(rs.findColumn("date")), rs.getInt("profile"));
								
				list.add(user);
			}		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<User>();
		}
		return list;
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
