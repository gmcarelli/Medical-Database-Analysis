package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.connection.Neo4JConnection;
import br.edu.ifsp.model.MyImage;

public class MyImageDAONeo4JTest {

	@Test
	public void insertImageTest() throws SQLException {
		MyImage myImage = new MyImage();

		myImage.setImageId(1);

		myImage.setImageName("DCC.TIFF");

		try {
			myImage.setImageBytes(DAOManagerNeo4J.myImageDAONeo4J().ImageFileToByteArray("imageSamples/DCC.tif"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query = "CREATE (n:MyImage { imageId : '"
				+ myImage.getImageId() + "', imageName : '"
				+ myImage.getImageName() + "', imageBytes : '"
					+ myImage.getImageBytes() + "' })";
		
		Neo4JConnection neo4jConnection = new Neo4JConnection();

		neo4jConnection.connect();

		assertTrue(neo4jConnection.executeUpdate(query));

	}

}