package bdd;

public interface SocialInt {
	public boolean isFriend(int id, int idShark);	
	public boolean addFriend(int id, int idShark);
	public boolean removeFriend(int id, int idShark);
}