package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
	public boolean addFriend(int id, int idShark) {
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		int nRows = 0;
		try {
			stmt = conn.createStatement();
			String requete = "INSERT INTO amis (idUser,idShark,date) " +
					"VALUES ('"+id+"','"+idShark+"',CURRENT_TIMESTAMP)";
			nRows = stmt.executeUpdate(requete);			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return (nRows != 0);
	}

	@Override
	public boolean removeFriend(int id, int idShark) {
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return false;
		}
		Statement stmt;
		int nRows = 0;
		try {
			stmt = conn.createStatement();
			String requete = "DELETE FROM amis" +
					" WHERE idUser='"+id+"' " +
					" AND idShark='"+idShark+"'";
			nRows = stmt.executeUpdate(requete);			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return (nRows != 0);
	}

}
