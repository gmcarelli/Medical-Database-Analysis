package br.edu.ifsp.connection;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongodbConnection {

	private static MongoClient mongoCliente = null;
	private DBCursor dbCursor;
	
	private MongodbConnection() {}

	public static MongoClient connect() throws UnknownHostException {
		
		if(MongodbConnection.mongoCliente == null) {
			
			MongodbConnection.mongoCliente =  new MongoClient("localhost", 27017);			
			
			return MongodbConnection.mongoCliente;
			
		} else {
			
			return MongodbConnection.mongoCliente;
			
		}

	}

	public boolean disconnect() throws Exception {

		boolean disconnect = false;
		
		if(MongodbConnection.mongoCliente != null) {
			
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
