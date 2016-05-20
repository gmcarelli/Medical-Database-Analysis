package br.edu.ifsp.connection;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.bson.Document;
import org.bson.types.Binary;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongodbConnection {

	private static MongoClient mongoClient = null;
	
	private static String mongoDatabaseName = "MedicalDatabaseAnalysis";
	
	private static MongoDatabase mongoDatabase;
	
	private static MongoCollection mongoCollection;
	
	private MongodbConnection() {
		
		//MongodbConnection.mongoCliente = new MongoClient("localhost", 27017);
		
	}
	
	public static MongoClient connect() throws UnknownHostException {		
			
		if(mongoClient == null) {
			
			mongoClient = new MongoClient("localhost", 27017);
			
			return mongoClient;
			
		}
		
		return 	mongoClient;

	}

	public static boolean disconnect() throws Exception {

		boolean disconnect = false;
		
		if(mongoClient != null) {
			
			mongoClient.close();			
			
			disconnect = true;
			
		}

		return disconnect;
	}
	
	public static boolean executeInsert(String collection, Document document) throws SQLException {
		
		mongoDatabase = mongoClient.getDatabase(mongoDatabaseName);

		mongoCollection = mongoDatabase.getCollection("MyImage");
		
		
		boolean writeResult = false;

		if (mongoClient != null) {

			

			try {

				mongoCollection.insertOne(document);
				
				writeResult = true;

			} catch (Exception e) {

				writeResult = false;

			}
			
		}
		
		this.mongoClient.close();
		
		return writeResult;
		
		return false;
		
	}
	
	public boolean executeUpdate(String collection, Document document) throws SQLException {

		return false;
	}
	
	public boolean executeDelete(String collection, Document document) throws SQLException {

		return false;
	}
}
