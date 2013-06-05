package neo4j;

import java.util.Hashtable;

import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import social.User;

public class N4JAdmin {
	private Hashtable<Integer, N4JUser> users;

	public N4JAdmin() {
		this.users = new Hashtable<Integer, N4JUser>();
	}
	
	public Hashtable<Integer, N4JUser> getUsers(){
		return this.users;
	}

	public N4JUser addUser(User user){
		N4JUser u = new N4JUser(user);
		users.put(u.getUser().getId(), u);
		return u;
	}

	public void removeUser(N4JUser user){
		Transaction tx = GraphDB.getGraphDb().beginTx();
		try{
			Iterable<Relationship> tmp = user.getUserNode().getRelationships();

			for(Relationship rel : tmp){
				rel.delete();
			}

			if(!user.getUserNode().hasRelationship()){
				user.getUserNode().delete();
				users.remove(user.getUser().getId());
			}else{
				System.out.println("probleme de rel");
			}
			tx.success();
		}finally{
			tx.finish();
		}		
	}
}
