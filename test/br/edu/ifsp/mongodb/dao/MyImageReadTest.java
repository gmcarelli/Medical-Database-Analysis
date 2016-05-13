package br.edu.ifsp.mongodb.dao;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.model.MyImage;

public class MyImageReadTest {

	@Test
	public void readTest() throws Exception {
		
		MyImage myImage = null;

		int imageId = 1;
		
		myImage = DAOManager.myImageDAOMongo().search(imageId);
		
		try {
			assertTrue(DAOManager.myImageDAONeo4J().byteArrayToTiffFile(myImage));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		assertTrue(myImage != null);
		
		assertTrue(myImage.getImageId() == 1);
		
		assertTrue(myImage.getImageName().equals("DML.TIFF"));
		
		assertTrue(myImage.getImageBytes().length == 11687936);
		
	}

}
