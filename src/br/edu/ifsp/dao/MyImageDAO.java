package br.edu.ifsp.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsp.connection.AConnection;
import br.edu.ifsp.model.MyImage;

public class MyImageDAO extends ImageFileDAO implements IDAO<MyImage> {

	private AConnection connection;
	//private String tableName = null;

	public MyImageDAO(AConnection connection) {
		
		this.connection = connection;
		
	}

	@Override
	public boolean insert(MyImage myImage) throws Exception {

		Map<String, Object> values = new HashMap<String, Object>();

		values.put("imageId", myImage.getImageId());
		values.put("imageName", myImage.getImageName());
		values.put("imageBytes", myImage.getImageBytes());

		return connection.executeInsert("MyImage", values);

	}

	@Override
	public boolean update(MyImage myImage) throws Exception {
		
		Map<String, Object> values = new HashMap<String, Object>();

		values.put("imageId", myImage.getImageId());
		values.put("imageName", myImage.getImageName());
		values.put("imageBytes", myImage.getImageBytes());
		
		return connection.executeUpdate("MyImage", values);
		
	}

	@Override
	public boolean delete(int imageId) throws Exception {
		
		return connection.executeDelete("MyImage", "imageId", imageId);
		
	}

	@Override
	public MyImage search(int imageId) throws Exception {

		return (MyImage) connection.executeSearch("MyImage", "imageId", imageId);
		
	}

	@Override
	public List<MyImage> list() throws Exception {
		
		List<Object> aux = connection.executeListData("MyImage");
		
		List<MyImage> myImageList = new ArrayList<>();
		
		for (Object object : aux) {
			
			myImageList.add((MyImage) object);
			
		}	
		
		return myImageList;
		
	}

}
