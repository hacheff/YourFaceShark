package neo4j;

import java.util.Collection;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import bdd.Profil;

public class TestCreaBase {
	public static void main(String[] args) {

		Node root;
		N4JAdmin admin = new N4JAdmin();
	
		Transaction tx = GraphDB.getGraphDb().beginTx();
		try {
			root = GraphDB.getGraphDb().createNode();
			root.setProperty("Nom", "UPMC");
			tx.success();
		} finally {
			tx.finish();
		}

		N4JUser cm = admin.addUser(Profil.getUserById(1));
		N4JUser hf = admin.addUser(Profil.getUserById(3));
		N4JUser bm = admin.addUser(Profil.getUserById(4));
		N4JUser fc = admin.addUser(Profil.getUserById(5));
		
		System.out.println("------------------------------------------------------");
		Collection<N4JUser> us = admin.getUsers().values();
		for(N4JUser i : us){
			System.out.println("Admin connais : "+i.getUser().getNom()+' '+i.getUser().getPrenom());
		}
		System.out.println("------------------------------------------------------");
		
		/*tx = GraphDB.getGraphDb().beginTx();
		try {
			cm.getUserNode().createRelationshipTo(root, RelTypes.AMI_AVEC);
			bm.getUserNode().createRelationshipTo(root, RelTypes.AMI_AVEC);
			hf.getUserNode().createRelationshipTo(root, RelTypes.AMI_AVEC);
			fc.getUserNode().createRelationshipTo(root, RelTypes.AMI_AVEC);
			tx.success();
		} finally {
			tx.finish();
		}*/
	
		bm.addAcquaintance(cm.getUserNode());
		bm.addAcquaintance(hf.getUserNode());
		hf.addAcquaintance(fc.getUserNode());

		
		System.out.println("------------------------------------------------------");
		Collection<Relationship> acq = bm.getRelTab().values();
		for(Relationship i : acq){
			System.out.println("BM est coupain avec : "+i.getEndNode().getProperty("nom")+' '+i.getEndNode().getProperty("prenom"));
		}
		
		SuggestRel mightKnow = new SuggestRel(bm.getUserNode(), 2);// get result from mightKnow.result
		
		for(int i=0; i<mightKnow.result.size(); i++){
			Node n = mightKnow.result.get(i).get(mightKnow.result.get(i).size()-1);
			System.out.println("Vous pourriez connaitre : " + n.getProperty("nom") +' '+ n.getProperty("prenom"));
		}
		
		GraphDB.getGraphDb().shutdown();
	}
}
