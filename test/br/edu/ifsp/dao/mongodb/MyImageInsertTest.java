package br.edu.ifsp.dao.mongodb;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.database.mongodb.MongodbDatabase;
import br.edu.ifsp.helper.ImageHelper;
import br.edu.ifsp.model.MyImage;

public class MyImageInsertTest {

	@Test
	public void insertTest() throws Exception {
		
		MyImageDAO myImageDAO = new MyImageDAO(new MongodbDatabase(null, null, null, null, 0));
					
		MyImage myImage = new MyImage();
		
		myImage.setImageId(1);
		
		myImage.setImageName("DML.TIFF");
		
		byte[] imageBytes = null;
		
		imageBytes = ImageHelper.imageFileToByteArray("imageSamples/DML.TIFF");
			
		myImage.setImageBytes(imageBytes);
		
		assertTrue(myImageDAO.insert(myImage));	
	}
}
