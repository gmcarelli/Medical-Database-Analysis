package br.edu.ifsp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.bson.Document;
import org.bson.types.Binary;

import com.mongodb.client.MongoCursor;

import br.edu.ifsp.database.Database;
import br.edu.ifsp.model.MyImage;

public class MyImageDAO extends DAO<MyImage> {

	public MyImageDAO(Database database) {
		this.database = database;
	}

	@Override
	public boolean insert(MyImage myImage) throws Exception {

		try {

			this.database.connect();

			return database.insert("MyImage", myImageToMap(myImage));

		} finally {

			this.database.disconnect();

		}
	}

	private Map<String, Object> myImageToMap(MyImage myImage) {
		
		Map<String, Object> values = new HashMap<String, Object>();

		values.put("imageId", myImage.getImageId());
		values.put("imageName", myImage.getImageName());
		values.put("imageBytes", myImage.getImageBytes());
		
		return values;
	}

	public boolean insert(List<MyImage> list) throws Exception {

		boolean result = true;

		try {

			this.database.connect();

			for (MyImage image : list)
				result &= database.insert("MyImage", myImageToMap(image));

			return result;

		} finally {

			this.database.disconnect();

		}
	}

	@Override
	public boolean update(MyImage myImage) throws Exception {

		boolean update = false;

		this.database.connect();

		Map<String, Object> values = new HashMap<String, Object>();

		values.put("imageId", myImage.getImageId());
		values.put("imageName", myImage.getImageName());
		values.put("imageBytes", myImage.getImageBytes());

		update = database.update("MyImage", values);

		this.database.disconnect();

		return update;
	}

	@Override
	public boolean delete(Integer id) throws Exception {

		boolean delete = false;

		try {

			this.database.connect();

			delete = database.delete("MyImage", "imageId", id);

			return delete;

		} finally {

			this.database.disconnect();
		}
	}

	@Override
	public MyImage searchById(Integer id) throws Exception {

		this.database.connect();

		Object result = database.searchById("MyImage", "imageId", id);
		
		MyImage myImage;
		
		if (result instanceof ResultSet)
			myImage = getMyImageFromId((ResultSet)result);

		else
			myImage = getMyImageFromId((Document)result);

		return myImage;
	}

	private MyImage getMyImageFromId(Document result) {
		
		Integer imageId = ((Document) result).getInteger("imageId");
		String imageName = ((Document) result).getString("imageName");
		byte[] bytes = ((Binary) ((Document) result).get("imageBytes")).getData();
		
		MyImage image = new MyImage();
		image.setImageId(imageId);
		image.setImageName(imageName);
		image.setImageBytes(bytes);
		
		return image;
	}

	private MyImage getMyImageFromId(ResultSet resultSet) throws Exception {
		
		Integer imageId = ((ResultSet) resultSet).getInt("imageId");
		String imageName = ((ResultSet) resultSet).getString("imageName");
		byte[] bytes = ((ResultSet) resultSet).getBytes("imageName");
		
		MyImage image = new MyImage();
		image.setImageId(imageId);
		image.setImageName(imageName);
		image.setImageBytes(bytes);
		
		return image;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MyImage> listAll() throws Exception {

		this.database.connect();

		List<MyImage> result = new ArrayList<MyImage>();

		Object queryResult = database.listAll("MyImage");

		if (queryResult != null) {

			if (queryResult instanceof ResultSet)
				result.addAll(this.resultToList((ResultSet)queryResult));
			
			else if (queryResult instanceof MongoCursor<?>)
				result.addAll(this.resultToList((MongoCursor<Document>)queryResult));
			

				
				this.database.disconnect();

		}

		return result;

	}

	private List<? extends MyImage> resultToList(MongoCursor<Document> mongoCursor) {
		
		List<MyImage> result = new ArrayList<MyImage>();
		
		while (mongoCursor.hasNext()) {

			Document document = mongoCursor.next();

			MyImage myImage = new MyImage();

			myImage.setImageId(document.getInteger("imageId"));

			myImage.setImageName(document.getString("imageName"));

			byte[] imageBytes = ((Binary) document.get("imageBytes")).getData();

			myImage.setImageBytes((imageBytes));

			result.add(myImage);
		}

		return result;
	}

	private List<? extends MyImage> resultToList(ResultSet resultSet) throws SQLException {

		List<MyImage> result = new ArrayList<MyImage>();

		try {

			while (resultSet.next()) {

				MyImage myImage = new MyImage();

				myImage.setImageId(resultSet.getInt("imageId"));

				myImage.setImageName(resultSet.getString("imageName"));

				Object object = resultSet.getObject("imageBytes");

				if (object instanceof String) {

					myImage.setImageBytes(Base64.decodeBase64((String) object));

				} else {

					myImage.setImageBytes((byte[]) object);

				}

				result.add(myImage);
			}
		
		} finally {
			resultSet.close();
		}

		return result;
	}

	@Override
	public boolean update(List<MyImage> list) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(List<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<MyImage> search(Set<Integer> ids) throws Exception {

		List<MyImage> list = new ArrayList<MyImage>();
		
		try {
			
			this.database.connect();

			for (Integer id : ids) {
			
				Object result = database.searchById("MyImage", "imageId", id);
				
				MyImage myImage;
				
				if (result instanceof ResultSet)
					myImage = getMyImageFromId((ResultSet)result);
		
				else
					myImage = getMyImageFromId((Document)result);
				
				list.add(myImage);
			}
		
		} finally {
			
			this.database.disconnect();
			
		}
		
		return list;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}
}
