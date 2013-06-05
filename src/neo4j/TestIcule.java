package neo4j;

import org.neo4j.graphdb.Node;

import social.User;
import bdd.Connexion;

public class TestIcule {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connexion.insertUser("Mumpert", "Clémence", "f", "c.mumpert@gmail.com", "21/05/1990", "clemence");
		Connexion.insertUser("Fontaine", "Hugo", "h", "c.mumpert@gmail.com", "21/05/1990", "hugo");
		Connexion.insertUser("Mouliac", "Bastien", "h", "c.mumpert@gmail.com", "21/05/1990", "hugo");

		User u = new User(1, 'h', "Fontaine", "Hugo","c.mumpert@gmail.com", null, 0);
		User u2 = new User(0, 'f', "Mumpert", "Clémence","c.mumpert@gmail.com", null, 0);
		User u3 = new User(0, 'h', "Mouliac", "Bastien","c.mumpert@gmail.com", null, 0);
		
		u.addFriend(u2);
		u2.addFriend(u3);
	}

}
