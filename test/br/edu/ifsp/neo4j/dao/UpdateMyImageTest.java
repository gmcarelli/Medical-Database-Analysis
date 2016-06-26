package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.connection.neo4j.Neo4jJDBCConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;

public class UpdateMyImageTest {

	@Test
	public void UpdateMyImagetest() throws SQLException {

		MyImageDAO myImageDAO = new MyImageDAO(new Neo4jJDBCConnection());

		MyImage myImage = new MyImage();

		myImage.setImageId(1);

		myImage.setImageName("ECC.TIFF");

		try {

			myImage.setImageBytes(myImageDAO.ImageFileToByteArray("imageSamples/DCC.TIFF"));

			System.out.println(myImage.getImageBytes().length);

		} catch (IOException e) {

			e.printStackTrace();

		}

		myImageDAO.insert(myImage);

		myImage = myImageDAO.search(myImage.getImageId());

		assertEquals("ECC.TIFF", myImage.getImageName());

		myImage.setImageName("DCC.TIFF");

		assertTrue(myImageDAO.update(myImage));

		myImage = myImageDAO.search(myImage.getImageId());

		assertEquals("DCC.TIFF", myImage.getImageName());

	}
}
