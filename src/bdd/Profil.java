package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import social.Post;
import social.User;

public class Profil {
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
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
	
	public static String getUrlPhotoProfile(int idPhoto){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return null;
		}
		Statement stmt;
		ResultSet rs = null;
		String url = "";
		try {
			stmt = conn.createStatement();
			String requete = "SELECT p.lien as 'url'" +
					" FROM PHOTOS p" +
					" WHERE idPhoto = '" + idPhoto + "'";
			
			rs = stmt.executeQuery(requete);

			while(rs.next()) {				
				url = rs.getString("url");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
		return url;
	}
	
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
			String requete = "SELECT p.texte as 'text', p.date as 'date', p.url as 'url'" +
					" FROM POST p" +
					" WHERE idPosteur = '" + idUser + "'" +					
					" ORDER BY date DESC" +
					" LIMIT " + debut + " , " + (debut + 30) + "";
			rs = stmt.executeQuery(requete);
			
			while(rs.next()) {
				Post post = new Post();
				post.setIdPosteur(idUser);
				post.setDate(rs.getDate(rs.findColumn("date")));
				post.setTexte(rs.getString("text"));
				post.setUrl(rs.getString("url"));
				
				list.add(post);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Post>();
		}
		return list;
	}
	
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
