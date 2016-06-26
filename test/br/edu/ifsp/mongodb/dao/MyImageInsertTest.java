package br.edu.ifsp.mongodb.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import br.edu.ifsp.connection.mongodb.MongodbConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;

public class MyImageInsertTest {

	@Test
	public void insertTest() {
		
		MyImageDAO myImageDAO = new MyImageDAO(new MongodbConnection());
					
		MyImage myImage = new MyImage();
		
		myImage.setImageId(1);
		
		myImage.setImageName("DML.TIFF");
		
		byte[] imageBytes = null;
		
		try {
			
			imageBytes = myImageDAO.ImageFileToByteArray("imageSamples/DML.TIFF");
			
			System.out.println(imageBytes.length);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myImage.setImageBytes(imageBytes);
		
		assertTrue(myImageDAO.insert(myImage));	
		
	}
}
