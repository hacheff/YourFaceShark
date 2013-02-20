package social;

import java.sql.Date;

public class User {

	private int id;
	private char sexe;
	private String nom;
	private String prenom;
	private String mail;
	private Date ddn;
	private String mdp;
	
	public User(int id, char sexe, String nom, String prenom, String mail, Date ddn, String mdp){
		this.id = id;
		this.sexe = sexe;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.ddn = ddn;
		this.mdp = mdp;
	}
	
	public int getId(){
		return this.id;
	}
	
	public char getSexe(){
		return this.sexe;
	}
	public void setNom(char sexe){
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
}
