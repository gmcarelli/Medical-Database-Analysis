package br.edu.ifsp.dao.neo4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.database.neo4j.Neo4jJDBCDatabase;
import br.edu.ifsp.helper.ImageHelper;
import br.edu.ifsp.model.MyImage;

public class UpdateMyImageTest {

	@Test
	public void UpdateMyImagetest() throws Exception {

		MyImageDAO myImageDAO = new MyImageDAO(new Neo4jJDBCDatabase(null, null, null, null, 0));

		MyImage myImage = new MyImage();

		myImage.setImageId(1);

		myImage.setImageName("ECC.TIFF");

		try {

			myImage.setImageBytes(ImageHelper.imageFileToByteArray("imageSamples/DCC.TIFF"));

			System.out.println(myImage.getImageBytes().length);

		} catch (IOException e) {

			e.printStackTrace();

		}

		myImageDAO.insert(myImage);

		myImage = myImageDAO.searchById(myImage.getImageId());

		assertEquals("ECC.TIFF", myImage.getImageName());

		myImage.setImageName("DCC.TIFF");

		assertTrue(myImageDAO.update(myImage));

		myImage = myImageDAO.searchById(myImage.getImageId());

		assertEquals("DCC.TIFF", myImage.getImageName());

	}
}
