package br.edu.ifsp.connection;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class MongodbConnection {

	private MongoClient mongoCliente = null;	
	
	public MongoClient connect() throws UnknownHostException {		
			
			this.mongoCliente =  new MongoClient("localhost", 27017);			
			
			return this.mongoCliente;	

	}

	public boolean disconnect() throws Exception {

		boolean disconnect = false;
		
		if(this.mongoCliente != null) {
			
			this.mongoCliente.close();			
			
			disconnect = true;
			
		}

		return disconnect;
	}
}
