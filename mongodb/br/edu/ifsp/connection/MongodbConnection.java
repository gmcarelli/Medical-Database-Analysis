package br.edu.ifsp.connection;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongodbConnection {

	private MongoClient mongoCliente = null;
	private DBCursor dbCursor = null;
	
	
	
	public MongoClient connect() throws UnknownHostException {		
			
			this.mongoCliente =  new MongoClient("localhost", 27017);			
			
			return this.mongoCliente;	

	}

	public boolean disconnect() throws Exception {

		boolean disconnect = false;
		
		if(this.mongoCliente != null) {
			
			this.mongoCliente.close();
			
			this.dbCursor.close();
			
			disconnect = true;
			
		}

		return disconnect;
	}

	public WriteResult executeQuery(BasicDBObject basicDBObject) {


		return null;
	}

	public void startTransaction() throws Exception {

	}

	public void commit() throws Exception {
		// TODO Auto-generated method stub

	}

	public void rollback() throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean executeUpdate(BasicDBObject basicDBObject) {

		

		return false;
	}

}
