package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import social.User;

import neo4j.N4JAdmin;
import neo4j.N4JUser;


public class Social implements SocialInt {

	public Social(){
		
	}
	
	@Override
	public boolean isFriend(int id, int idShark) {
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			String requete = "SELECT COUNT(*) as nb" +
				" FROM amis" +
				" WHERE idUser = " + id +
				" AND idShark = " + idShark;

			rs = stmt.executeQuery(requete);
			while(rs.next()) {
				if(rs.getInt("nb") != 0){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean addFriend(User user, User userShark) {
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		int nRows = 0;
		try {
			stmt = conn.createStatement();
			String requete = "INSERT INTO amis (idUser,idShark,date) " +
					"VALUES ('"+user.getId()+"','"+userShark.getId()+"',CURRENT_TIMESTAMP)";
			nRows = stmt.executeUpdate(requete);			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		N4JAdmin admin = Bdd.connectNeo4j();
		N4JUser nUser = new N4JUser(user);
		N4JUser nShark = new N4JUser(userShark);
		nUser.addAcquaintance(nShark.getUserNode());
		
		return (nRows != 0);
	}

	@Override
	public boolean removeFriend(User user, User userShark) {
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		int nRows = 0;
		try {
			stmt = conn.createStatement();
			String requete = "DELETE FROM amis" +
					" WHERE idUser='"+user.getId()+"' " +
					" AND idShark='"+userShark.getId()+"'";
			nRows = stmt.executeUpdate(requete);			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return (nRows != 0);
	}

}
