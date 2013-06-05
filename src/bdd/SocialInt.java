package bdd;

import social.User;

public interface SocialInt {
	
	/**
	 * Test si les 2 USER sont amis ou pas
	 * @param id
	 * @param idShark
	 * @return 
	 */
	public boolean isFriend(int id, int idShark);
	
	/**
	 * Ajoute idShark dans la liste d'amis de id
	 * @param id
	 * @param idShark
	 * @return
	 */
	public boolean addFriend(User id, User idShark);
	
	/**
	 * Retire idShark dans la liste d'amis de id
	 * @param id
	 * @param idShark
	 * @return
	 */
	public boolean removeFriend(User id, User idShark);
}