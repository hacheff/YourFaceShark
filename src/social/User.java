package social;

import java.sql.Date;

import bdd.Profil;
import bdd.Social;
import bdd.SocialInt;

public class User {

	private int id;
	private char sexe;
	private String nom;
	private String prenom;
	private String mail;
	private Date ddn;
	private String mdp;
	private int idPhoto;
	
	public User(int id, char sexe, String nom, String prenom, String mail, Date ddn, String mdp, int idPhoto){
		this.id = id;
		this.sexe = sexe;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.ddn = ddn;
		this.mdp = mdp;
		this.idPhoto = idPhoto;
	}
	
	public User(int id, char sexe, String nom, String prenom, String mail, Date ddn, int idPhoto){
		this.id = id;
		this.sexe = sexe;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.ddn = ddn;
		this.mdp = null;
		this.idPhoto = idPhoto;
	}
	
	public int getId(){
		return this.id;
	}
	
	public char getSexe(){
		return this.sexe;
	}
	public void setSexe(char sexe){
		this.sexe = sexe;
	}
	
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public String getPrenom(){
		return this.prenom;
	}
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	
	public String getMail(){
		return this.mail;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	
	public Date getDate(){
		return this.ddn;
	}
	public void setDate(Date ddn){
		this.ddn = ddn;
	}
	
	public String getMdp(){
		return this.mdp;
	}
	public void setMdp(String mdp){
		this.mdp = mdp;
	}
	
	public int getUrl(){
		return this.idPhoto;
	}
	public void setUrl(int idPhoto){
		this.idPhoto = idPhoto;
	}
	
	/**
	 * Test si l'utilisateur passé en paramètre est amis avec this
	 * @param u
	 * @return true si u est amis false sinon
	 */
	public boolean isFriend(User u){
		SocialInt social = new Social();
		return social.isFriend(this.id, u.getId());
	}
	
	/**
	 * Ajoute un amis
	 * @param u
	 * @return true si l'ajout s'est bien effectué
	 */
	public boolean addFriend(User u){
		SocialInt social = new Social();
		return social.addFriend(this.id, u.getId());
	}
	
	/**
	 * Suuprime un amis
	 * @param u
	 * @return true si la suppression s'est bien effectué
	 */
	public boolean removeFriend(User u){
		SocialInt social = new Social();
		return social.removeFriend(this.id, u.getId());
	}
	
	public String getUrlProfile(){
		return Profil.getUrlPhotoProfile(this.id);
	}
}
