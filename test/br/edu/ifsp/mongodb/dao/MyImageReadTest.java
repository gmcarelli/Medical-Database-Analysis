package br.edu.ifsp.mongodb.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import br.edu.ifsp.connection.MongodbConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;

public class MyImageReadTest {

	@Test
	public void readTest() throws Exception {
		
		MyImageDAO myImageDAO = new MyImageDAO(new MongodbConnection());
		
		MyImage myImage = null;

		int imageId = 1;
		
		myImage = myImageDAO.search(imageId);
		
		try {
			assertTrue(myImageDAO.byteArrayToTiffFile(myImage));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		assertTrue(myImage != null);
		
		assertTrue(myImage.getImageId() == 1);
		
		assertTrue(myImage.getImageName().equals("DML.TIFF"));
		
		assertTrue(myImage.getImageBytes().length == 11687936);
		
	}

}
