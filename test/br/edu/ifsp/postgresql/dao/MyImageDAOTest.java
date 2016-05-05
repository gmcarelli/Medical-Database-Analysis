package br.edu.ifsp.postgresql.dao;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.model.MyImage;

public class MyImageDAOTest {
	
	@Test
	public void ImageFileToByteArrayTest() throws IOException {
		String imageUrl = "imageSamples/DCC.TIFF";
		
		byte[] imageBytes = DAOManagerPostgre.myImageDAO().ImageFileToByteArray(imageUrl);
		
		System.out.println(imageBytes.length);
		
		assertTrue(imageBytes.length > 0);
	}
	
	@Test
	public void insertImageTest() throws SQLException {
		MyImage myImage = new MyImage();
		
		myImage.setImageName("DCC.TIFF");
		
		try {
			
			myImage.setImageBytes(DAOManagerPostgre.myImageDAO().ImageFileToByteArray("imageSamples/DCC.tif"));
			
			assertTrue(DAOManagerPostgre.myImageDAO().insert(myImage));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void readImageFromDBTest() throws SQLException {
		int imageId = DAOManagerPostgre.myImageDAO().getUltimoIdCadastrado("image", "imageId");
		
		MyImage myImage = DAOManagerPostgre.myImageDAO().search(imageId);	
		
		assertTrue(myImage.getImageBytes() != null);
		assertTrue(myImage.getImageBytes().length > 0);	
		
	}
}