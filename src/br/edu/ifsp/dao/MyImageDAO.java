package br.edu.ifsp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsp.connection.AConnection;
import br.edu.ifsp.model.MyImage;

public class MyImageDAO implements IDAO<MyImage> {
	
	private AConnection connection;
	
	public MyImageDAO(AConnection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insert(MyImage myImage) throws Exception {
		
		
		Map<String, Object> values = new HashMap<String, Object>();
		
		values.put("imageId",    myImage.getImageId());
		values.put("imageName",  myImage.getImageName());
		values.put("imageBytes", myImage.getImageBytes());		
		
		return connection.executeInsert("MyImage", values);
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
	public MyImage search(int objectId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MyImage> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
