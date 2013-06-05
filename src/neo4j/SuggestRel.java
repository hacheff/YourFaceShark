package neo4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class SuggestRel {
	//la profondeur de la recherche en terme de noeud, a partir du premier ami
	//ex : si 3, on cherchera jusqu'a l'ami de l'ami de l'ami
	public int maxDepth;
	public Set<Node> parsed = new HashSet<Node>();
	public Set<Node> buddies = new HashSet<Node>();
	public List<List<Node>> result = new ArrayList<List<Node>>();
	
	public SuggestRel(Node origin, int maxDepth){
		this.maxDepth = maxDepth;
		for (Relationship rel : origin.getRelationships()){
			buddies.add(rel.getOtherNode(origin));
		}
		parsed = buddies;
		findFriends(origin, Arrays.asList(new Node[]{origin}),1);
	}

	public void findFriends(Node rootNode, List<Node> path, int depth){
		for(Relationship rel : rootNode.getRelationships()){
			Node currNode = rel.getOtherNode(rootNode);
			if((depth>1 && parsed.contains(currNode)) || path.contains(currNode)){
				continue;
			}else{
				List<Node> newPath = new ArrayList<Node>(path);
				newPath.add(currNode);
				parsed.add(currNode);
				if(depth>1){
					result.add(newPath);
				}if(depth != maxDepth){
					findFriends(currNode, newPath, depth + 1);
				}
			}
		}
	}
}
