package neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public final class GraphDB {
	
	private static final String DB_PATH = "/neo4j/database/workties.db";
	private static GraphDatabaseService graphDb;
	
	private GraphDB(){
		GraphDB.graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
		registerShutdownHook(graphDb);
	}
	
	//registerShutDownHook permet de fermer proprement la connexion a la BD mm en cas de crash de la JVM
	private static void registerShutdownHook( final GraphDatabaseService graphDb ){
		Runtime.getRuntime().addShutdownHook( new Thread(){
			@Override
			public void run(){
				graphDb.shutdown();
			}
		} );
	}

	public static GraphDatabaseService getGraphDb(){
		if (graphDb == null) {
			new GraphDB();
		}
		return graphDb;	
	}
}
