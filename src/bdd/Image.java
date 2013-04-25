package bdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Image {
	public static byte[] getBlob(int id){
		Connection conn = Bdd.connectBdd();
		if(conn == null){
			return null;
		}
		Statement stmt;
		byte[] img	 = null;
		Blob blob;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT donnees FROM Photos WHERE idPhoto='"+id+"'");
			while(rs.next()) {
				blob = rs.getBlob(1);
				img = blob.getBytes(1,(int)blob.length());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return img;
	}
	
	public static boolean insertPhoto(String location, int idUser)
	{
		int nb = 0;
		try {
			File monImage = new File(location);
			FileInputStream istreamImage = new FileInputStream(monImage);
			Connection conn = Bdd.connectBdd();
			try {
				PreparedStatement ps = conn.prepareStatement("INSERT INTO photos(idUser,donnees) VALUES (?,?)");
				ps.setInt(1, idUser);
				ps.setBinaryStream(2, istreamImage, istreamImage.available());
				nb = ps.executeUpdate();
				ps.close();
				monImage.delete();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			istreamImage.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		if(nb == 1){
			return true;
		}
		else{
			return false;
		}
	}
}
