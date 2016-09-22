package br.edu.ifsp.dao.mongodb;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.database.mongodb.MongodbDatabase;
import br.edu.ifsp.helper.ImageHelper;
import br.edu.ifsp.model.MyImage;

public class MyImageReadTest {

	@Test
	public void readTest() throws Exception {
		
		MyImageDAO myImageDAO = new MyImageDAO(new MongodbDatabase(null, null, null, null, 0));
		
		MyImage myImage = null;

		int imageId = 1;
		
		myImage = myImageDAO.searchById(imageId);
		
		assertTrue(ImageHelper.byteArrayToTiffFile(myImage));
		
		assertTrue(myImage != null);
		
		assertTrue(myImage.getImageId() == 1);
		
		assertTrue(myImage.getImageName().equals("DML.TIFF"));
		
		assertTrue(myImage.getImageBytes().length == 11687936);
		
	}

}
