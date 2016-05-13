package br.edu.ifsp.mongodb.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.model.MyImage;

public class MyImageInsertTest {

	@Test
	public void insertTest() {
					
		MyImage myImage = new MyImage();
		
		myImage.setImageId(1);
		
		myImage.setImageName("DML.TIFF");
		
		byte[] imageBytes = null;
		
		try {
			imageBytes = DAOManager.myImageDAOMongo().ImageFileToByteArray("imageSamples/DML.TIFF");
			
			System.out.println(imageBytes.length);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myImage.setImageBytes(imageBytes);
		
		assertTrue(DAOManager.myImageDAOMongo().insert(myImage));	
		
	}
}
