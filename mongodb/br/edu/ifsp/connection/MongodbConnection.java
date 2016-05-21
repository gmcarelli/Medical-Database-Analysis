package br.edu.ifsp.connection;

import java.util.ArrayList;
import java.util.List;
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

			((MongodbConnection) this.connection).disconnect();
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

			String col = set.iterator().next();

			resultDocument = mongoCollection.findOneAndUpdate(new BasicDBObject(col, values.get(col)), document);

			((MongodbConnection) this.connection).disconnect();

		}

		return resultDocument != null;

	}

	@Override
	public boolean executeDelete(String tableName, String col, int objectId) {

		Document resultDocument = null;

		if (this.connection != null) {

			MongoDatabase mongoDatabase = ((MongoClient) this.connection).getDatabase(this.mongoDatabaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);

			resultDocument = mongoCollection.findOneAndDelete(new BasicDBObject(col, objectId));
			
			((MongodbConnection) this.connection).disconnect();
			
		}

		return resultDocument != null;
	}

	@Override
	public Object executeSearch(String tableName, String col, int objectId) {

		Document document = null;

		if (this.connection != null) {

			MongoDatabase mongoDatabase = ((MongoClient) this.connection).getDatabase(this.mongoDatabaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);

			document = mongoCollection.find(new BasicDBObject(col, objectId)).first();

			((MongodbConnection) this.connection).disconnect();
		}

		return document;
	}

	@Override
	public List<Object> executeListData(String tableName) {

		List<Object> objectList = new ArrayList<>();

		if (this.connection != null) {

			MongoDatabase mongoDatabase = ((MongoClient) this.connection).getDatabase(this.mongoDatabaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);

			FindIterable<Document> documentList = mongoCollection.find();

			while (documentList.iterator().hasNext()) {

				Document document = documentList.iterator().next();				

				objectList.add(document);

			}

			((MongodbConnection) this.connection).disconnect();
			
		}
		
		return objectList;
	}

	@Override
	public int getLastInsertedId(String tableName, String col, int objectId) {
		// TODO Auto-generated method stub
		return 0;
	}
}
