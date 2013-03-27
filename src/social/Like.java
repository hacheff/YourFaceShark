package social;

public class Like {
	private int idLike;
    private int idPost;
    private int like;
    private int dislike;
   
    public Like(){}
   
    public Like(int id){
        this.idLike = id;
    }
   
    public void setIdLike(int id){
        this.idLike = id;
    }
   
    public void setIdPost(int id){
        this.idPost = id;
    }
   
    public void setLike(int nb){
        this.like = nb;
    }
   
    public void setDislike(int nb){
        this.dislike = nb;
    }
   
    public int getIdLike(){
        return this.idLike;
    }
   
    public int getIdPost(){
        return this.idPost;
    }
   
    public int getLike(){
        return this.like;
    }
   
    public int getDislike(){
        return this.dislike;
    }
}
