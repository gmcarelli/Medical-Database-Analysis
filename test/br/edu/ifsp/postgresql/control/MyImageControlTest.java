package br.edu.ifsp.postgresql.control;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.postgresql.dao.DAOManager;

public class MyImageControlTest {
	
	@Test
	public void insertImageTest() throws SQLException {
		MyImage myImage = new MyImage();
		
		myImage.setImageName("DCC.TIFF");
		
		try {
			
			myImage.setImageBytes(DAOManager.myImageDAO().ImageFileToByteArray("imageSamples/DCC.tif"));
			
			assertTrue(DAOManager.myImageDAO().insert(myImage));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void readImageFromDBTest() throws SQLException {
		int imageId = DAOManager.myImageDAO().getUltimoIdCadastrado("image", "imageId");
		
		MyImage myImage = DAOManager.myImageDAO().search(imageId);	
		
		assertTrue(myImage.getImageBytes() != null);
		assertTrue(myImage.getImageBytes().length > 0);	
		
	}
}