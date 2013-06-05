package neo4j;

import java.util.Hashtable;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import social.User;

public class N4JUser {

	private User user;
	private Node userNode;
	private Hashtable<Node, Relationship> reltab;

	public N4JUser(User u) {
		this.user = u;
		this.reltab = new Hashtable<Node, Relationship>();

		Transaction tx = GraphDB.getGraphDb().beginTx();
		try {
			this.userNode = GraphDB.getGraphDb().createNode();
			userNode.setProperty("id", u.getId());
			userNode.setProperty("nom", u.getNom());
			userNode.setProperty("prenom", u.getPrenom());
			tx.success();
		} finally {
			tx.finish();
		}
	}

	public User getUser() {
		return this.user;
	}
	
	public Node getUserNode() {
		return this.userNode;
	}
	
	public Hashtable<Node, Relationship> getRelTab() {
		return this.reltab;
	}

	public void addAcquaintance(Node target) {
		Transaction tx = GraphDB.getGraphDb().beginTx();
		try {						
			Relationship coll = this.userNode.createRelationshipTo(target, RelTypes.AMI_AVEC);
			this.reltab.put(target, coll);
			
			tx.success();
		} finally {
			tx.finish();
		}
	}

	public void supprAcquaintance(Node target) {
			this.reltab.remove(target);
	}
	
	public static String graphe(User user){
		StringBuffer sb = new StringBuffer();
		N4JUser nUser = new N4JUser(user);
		SuggestRel mightKnow = new SuggestRel(nUser.getUserNode(), 2);// get result from mightKnow.result
		
		for(int i=0; i<mightKnow.result.size(); i++){
			Node n = mightKnow.result.get(i).get(mightKnow.result.get(i).size()-1);
			sb.append("Vous pourriez connaitre : " + n.getProperty("nom") +' '+ n.getProperty("prenom"));
		}
		
		return sb.toString();
	}
}
