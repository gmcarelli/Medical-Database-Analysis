package br.edu.ifsp.mongodb.dao;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

import br.edu.ifsp.connection.MongodbConnection;
import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ImageFileDAO;
import br.edu.ifsp.model.MyImage;

public class MyImageDAOMongo_alt extends ImageFileDAO implements IDAO<MyImage> {

	private MongoClient mongoClient;

	public MyImageDAOMongo_alt() {

		try {

			this.mongoClient = MongodbConnection.connect();

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}

	}

	@Override
	public boolean insert(MyImage myImage) {

		WriteResult writeResult = null;

		if (this.mongoClient != null) {

			DBCollection dbCollection = this.mongoClient.getDB("MedicalDatabaseAnalysis").getCollection("MyImage");

			DBObject dbObject = new BasicDBObject();

			String imageBytes = Base64.encodeBase64String(myImage.getImageBytes());

			dbObject.put("imageId", myImage.getImageId());
			dbObject.put("imageName", myImage.getImageName());
			dbObject.put("imageBytes", imageBytes);

			writeResult = dbCollection.insert(dbObject);

		}

		return writeResult != null;

	}

	@Override
	public boolean update(MyImage object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(MyImage object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MyImage search(int objectId) {

		DBCursor dbCursor = null;

		MyImage myImage = null;

		if (this.mongoClient != null) {

			dbCursor = this.mongoClient.getDB("MedicalDatabaseAnalysis").getCollection("MyImage")
					.find(new BasicDBObject("imageId", objectId));

			if (dbCursor.hasNext()) {

				DBObject object = dbCursor.next();

				System.out.println(object.get("imageId"));

				myImage = new MyImage();

				myImage.setImageId((int) object.get("imageId"));

				myImage.setImageName((String) object.get("imageName"));

				String imageBytes = (String) object.get("imageBytes");

				myImage.setImageBytes(Base64.decodeBase64(imageBytes));

			}

		}

		return myImage;
	}

	@Override
	public List<MyImage> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
