package social;

import java.sql.Date;

public class Post {
	private int idPost;
    private int idPosteur;
    private int idCible;
    private String texte;
    private String url;
    private Date date;    
	private int nbCom;
   
    public Post(){}
   
    public Post(int id){
        this.idPost = id;
    }
   
    public void setIdPost(int id){
        this.idPost = id;
    }
   
    public void setIdPosteur(int id){
        this.idPosteur = id;
    }
   
    public void setIdCible(int id){
        this.idCible = id;
    }
   
    public void setTexte(String txt){
        this.texte = txt;
    }
   
    public void setUrl(String url){
        this.url = url;
    }
   
    public void setDate(Date date){
        this.date = date;
    }
   
    public int getIdPost(){
        return this.idPost;
    }
   
    public int getIdPosteur(){
        return this.idPosteur;
    }
   
    public int getIdCible(){
        return this.idCible;
    }
   
    public String getTexte(){
        return this.texte;
    }
   
    public String getUrl(){
        return this.url;
    }
   
    public Date getDate(){
        return this.date;
    }

	public int getNbCom() {
		return nbCom;
	}

	public void setNbCom(int nbCom) {
		this.nbCom = nbCom;
	}
}
