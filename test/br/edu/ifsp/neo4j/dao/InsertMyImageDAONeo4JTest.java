package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4jJDBCConnection;

public class InsertMyImageDAONeo4JTest {

	@Test
	public void insertImageTest() throws UnsupportedEncodingException {
		
		MyImageDAO myImageDAO = new MyImageDAO(new Neo4jJDBCConnection());
		
		MyImage myImage = new MyImage();

		myImage.setImageId(1);

		myImage.setImageName("ECC.TIFF");

		try {
			myImage.setImageBytes(myImageDAO.ImageFileToByteArray("imageSamples/ECC.TIFF"));

			System.out.println(myImage.getImageBytes().length);
			
		} catch (IOException e) {
			e.printStackTrace();
		}						
		
		assertTrue(myImageDAO.insert(myImage));

	}	
		
}