package br.edu.ifsp.mongodb.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.Binary;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.edu.ifsp.connection.MongodbConnection;
import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ImageFileDAO;
import br.edu.ifsp.model.MyImage;

public class MyImageDAOMongo extends ImageFileDAO implements IDAO<MyImage> {

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> mongoCollection;

	public MyImageDAOMongo() {

		try {

			this.mongoClient = MongodbConnection.connect();

		} catch (UnknownHostException e) {

			e.printStackTrace();

		}

		this.mongoDatabase = mongoClient.getDatabase("MedicalDatabaseAnalysis");

		this.mongoCollection = mongoDatabase.getCollection("MyImage");

	}

	@Override
	public boolean insert(MyImage myImage) {

		boolean writeResult = false;

		if (this.mongoClient != null) {

			Document document = new Document().append("imageId", myImage.getImageId())
					.append("imageName", myImage.getImageName())
					.append("imageBytes", new Binary(myImage.getImageBytes()));

			try {

				this.mongoCollection.insertOne(document);
				
				writeResult = true;

			} catch (Exception e) {

				writeResult = false;

			}
			
		}
		
		this.mongoClient.close();
		
		return writeResult;

	}

	@Override
	public boolean update(MyImage myImage) throws Exception {
		
		Document document = null;

		if (this.mongoClient != null) {

			MongoCollection<Document> mongoCollection = mongoClient.getDatabase("MedicalDatabaseAnalysis")
					.getCollection("MyImage");
			
			Document newDocument = new Document().append("imageId", myImage.getImageId())
					.append("imageName", myImage.getImageName())
					.append("imageBytes", new Binary(myImage.getImageBytes()));

			document = mongoCollection.findOneAndUpdate(new BasicDBObject("imageId", myImage.getImageId()), newDocument);
		}

		this.mongoClient.close();
		
		return document != null;
	}

	@Override
	public boolean delete(MyImage myImage) throws Exception {

		Document document = null;

		if (this.mongoClient != null) {

			MongoCollection<Document> mongoCollection = mongoClient.getDatabase("MedicalDatabaseAnalysis")
					.getCollection("MyImage");

			document = mongoCollection.findOneAndDelete(new BasicDBObject("imageId", myImage.getImageId()));
		}
		
		this.mongoClient.close();

		return document != null;

	}

	@Override
	public MyImage search(int imageId) {

		MyImage myImage = null;

		if (this.mongoClient != null) {

			MongoCollection<Document> mongoCollection = mongoClient.getDatabase("MedicalDatabaseAnalysis")
					.getCollection("MyImage");

			Document document = mongoCollection.find(new BasicDBObject("imageId", imageId)).first();

			if (document != null) {

				myImage = new MyImage();

				myImage.setImageId((int) document.get("imageId"));

				myImage.setImageName((String) document.get("imageName"));

				byte[] imageBytes = (byte[]) document.get("imageBytes");

				myImage.setImageBytes((imageBytes));

			}

		}
		
		this.mongoClient.close();

		return myImage;
	}

	@Override
	public List<MyImage> list() throws Exception {

		List<MyImage> myImageList = new ArrayList<>();

		if (this.mongoClient != null) {

			MongoCollection<Document> mongoCollection = mongoClient.getDatabase("MedicalDatabaseAnalysis")
					.getCollection("MyImage");

			FindIterable<Document> documentList = mongoCollection.find();

			MyImage myImage;

			while (documentList.iterator().hasNext()) {

				Document document = documentList.iterator().next();

				myImage = new MyImage();

				myImage.setImageId((int) document.get("imageId"));

				myImage.setImageName((String) document.get("imageName"));

				byte[] imageBytes = (byte[]) document.get("imageBytes");

				myImage.setImageBytes((imageBytes));
				
				myImageList.add(myImage);

			}

		}
		
		this.mongoClient.close();

		return myImageList;
	}
}
