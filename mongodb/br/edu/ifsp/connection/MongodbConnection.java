package br.edu.ifsp.connection;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class MongodbConnection {

	private static MongoClient mongoCliente = null;
	
	private MongodbConnection() {
		
		MongodbConnection.mongoCliente = new MongoClient("localhost", 27017);
		
	}
	
	public static MongoClient connect() throws UnknownHostException {		
			
		if(MongodbConnection.mongoCliente == null) {
			
			new MongodbConnection();
			
			return MongodbConnection.mongoCliente;
			
		}
		
		return 	MongodbConnection.mongoCliente;

	}

	public static boolean disconnect() throws Exception {

		boolean disconnect = false;
		
		if(MongodbConnection.mongoCliente != null) {
			
			MongodbConnection.mongoCliente.close();			
			
			disconnect = true;
			
		}

		return disconnect;
	}
}
