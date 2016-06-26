package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import br.edu.ifsp.connection.neo4j.Neo4jJDBCConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;

public class DeleteMyImageTest {

	@Test
	public void deleteMyImagetest() {
		
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
		
		assertTrue(myImageDAO.delete(myImage.getImageId()));
		
	}

}
