package br.edu.ifsp.database.mongodb;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.bson.Document;
import org.bson.types.Binary;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import br.edu.ifsp.database.Database;

public class MongodbDatabase extends Database {
	
	public MongodbDatabase(String databaseName, String username, String password, String host, Integer port) {
		super(databaseName, username, password, host, port);
	}

	public MongodbDatabase(String propertiesPath) throws Exception {
		super(propertiesPath);
	}

	private MongoClient connection;

	public MongodbDatabase(String databaseName, String username, String password, String host, int port) {
		super(databaseName, username, password, host, port);
	}

	public MongodbDatabase(String databaseName, String username, String password) {
		super(databaseName, username, password, "localhost", 27017);
	}

	public MongodbDatabase(Properties properties) {
		super(properties);
	}

	@Override
	public boolean connect() {
		
		this.connection = new MongoClient(super.host, super.port);
		
		return this.connection != null;
	}

	@Override
	public void disconnect() {

		if (this.connection != null)
			this.connection.close();
	}

	@Override
	public boolean insert(String collection, Map<String, Object> values) {

		boolean writeResult = false;

		if (this.connection != null) {

			com.mongodb.client.MongoDatabase mongoDatabase = this.connection.getDatabase(super.databaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(super.databaseName);

			Document document = new Document();

			for (String key : values.keySet()) {

				if (values.get(key) instanceof byte[])
					document.append(key, new Binary((byte[]) values.get(key)));

				else
					document.append(key, values.get(key));
			}

			mongoCollection.insertOne(document);

			writeResult = true;
		}

		return writeResult;

	}

	@Override
	public boolean update(String tableName, Map<String, Object> values) {

		Document resultDocument = null;

		if (this.connection != null) {

			Document document = new Document();

			for (String key : values.keySet())
				document.append(key, values.get(key));

			com.mongodb.client.MongoDatabase mongoDatabase = ((MongoClient) this.connection)
					.getDatabase(super.databaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);

			Set<String> set = values.keySet();

			String column = set.iterator().next();

			resultDocument = mongoCollection.findOneAndUpdate(new BasicDBObject(column, values.get(column)), document);
		}

		return resultDocument != null;

	}

	@Override
	public boolean delete(String tableName, String column, Integer objectId) throws Exception {

		Document resultDocument = null;

		if (this.connection != null) {

			com.mongodb.client.MongoDatabase mongoDatabase = ((MongoClient) this.connection)
					.getDatabase(super.databaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);

			resultDocument = mongoCollection.findOneAndDelete(new BasicDBObject(column, objectId));

		}

		return resultDocument != null;
	}

	@Override
	public Document searchById(String collection, String column, Integer id) {

		Document document = null;

		if (this.connection != null) {

			MongoDatabase mongoDatabase = this.connection.getDatabase(this.databaseName);

			MongoCollection<Document> mongoCollection =  mongoDatabase.getCollection(this.databaseName);

			document = mongoCollection.find(new BasicDBObject(column, id)).first();
			
		}

		return document;
	}

	@Override
	public MongoCursor<Document> listAll(String collection) {

		MongoCursor<Document> result = null;
		
		if (this.connection != null) {

			com.mongodb.client.MongoDatabase mongoDatabase = ((MongoClient) this.connection)
					.getDatabase(super.databaseName);

			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);

			result = mongoCollection.find().iterator();
		}

		return result;
	}

	@Override
	public boolean deleteAll(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}
