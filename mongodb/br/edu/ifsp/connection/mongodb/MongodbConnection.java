package br.edu.ifsp.connection.mongodb;

import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.bson.types.Binary;

import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.edu.ifsp.connection.AConnection;

public class MongodbConnection extends AConnection {

	private final String mongoDatabaseName = "MedicalDatabaseAnalysis";
	// private static MongoDatabase mongoDatabase;
	// private static MongoCollection mongoCollection;	

	@Override
	public Object connect() {

		this.connection = new MongoClient("localhost", 27017);

		return this.connection;

	}

	@Override
	public boolean disconnect() {

		boolean disconnect = false;

		if (this.connection != null) {

			((Mongo) this.connection).close();

			disconnect = true;

		}

		return disconnect;
	}

	@Override
	public boolean executeInsert(String tableName, Map<String, Object> values) {

		boolean writeResult = false;

		if (this.connection != null) {

			MongoDatabase mongoDatabase = ((MongoClient) this.connection).getDatabase(this.mongoDatabaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);

			Document document = new Document();

			for (String key : values.keySet()) {

				if (values.get(key) instanceof byte[]) {

					document.append(key, new Binary((byte[]) values.get(key)));

				} else {

					document.append(key, values.get(key));

				}
				
			}

			try {

				mongoCollection.insertOne(document);

			} catch (Exception e) {

				writeResult = false;

			} finally {

				writeResult = true;
				
			}
			
		}

		return writeResult;

	}

	@Override
	public boolean executeUpdate(String tableName, Map<String, Object> values) {

		Document resultDocument = null;

		if (this.connection != null) {

			Document document = new Document();

			for (String key : values.keySet()) {

				document.append(key, values.get(key));

			}

			MongoDatabase mongoDatabase = ((MongoClient) this.connection).getDatabase(this.mongoDatabaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);

			Set<String> set = values.keySet();

			String column = set.iterator().next();

			resultDocument = mongoCollection.findOneAndUpdate(new BasicDBObject(column, values.get(column)), document);

		}

		return resultDocument != null;

	}

	@Override
	public boolean executeDelete(String tableName, String column, int objectId) {

		Document resultDocument = null;

		if (this.connection != null) {

			MongoDatabase mongoDatabase = ((MongoClient) this.connection).getDatabase(this.mongoDatabaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);

			resultDocument = mongoCollection.findOneAndDelete(new BasicDBObject(column, objectId));
			
		}

		return resultDocument != null;
	}

	@Override
	public Object executeSearch(String tableName, String column, int objectId) {

		Document document = null;

		if (this.connection != null) {

			MongoDatabase mongoDatabase = ((MongoClient) this.connection).getDatabase(this.mongoDatabaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);

			document = mongoCollection.find(new BasicDBObject(column, objectId)).first();
		}

		return document;
	}

	@Override
	public Object executeListData(String tableName) {

		FindIterable<Document> findIterable = null;

		if (this.connection != null) {

			MongoDatabase mongoDatabase = ((MongoClient) this.connection).getDatabase(this.mongoDatabaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);

			findIterable = mongoCollection.find();
			
		}
		
		return findIterable;
	}

	@Override
	public int getLastInsertedId(String tableName, String pkColumn) {
		// TODO Auto-generated method stub
		return 0;
	}
}
