package br.edu.ifsp.mongodb.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;


import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ImageFileDAO;
import br.edu.ifsp.model.MyImage;

public class MyImageDAOMongo extends ImageFileDAO implements IDAO<MyImage> {

	

	public MyImageDAOMongo() {

//		try {
//
//			
//
//		} catch (UnknownHostException e) {
//
//			e.printStackTrace();
//		}

	}

	@Override
	public boolean insert(MyImage myImage) {

		

		return false;

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

		
		return null;
	}

	@Override
	public List<MyImage> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
