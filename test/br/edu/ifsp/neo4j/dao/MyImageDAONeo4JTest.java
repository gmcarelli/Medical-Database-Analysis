package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class MyImageDAONeo4JTest {

	@Test
	public void insertImageTest() throws SQLException {
		MyImage myImage = new MyImage();

		myImage.setImageId(1);

		myImage.setImageName("DCC.TIFF");

		try {
			myImage.setImageBytes(DAOManager.myImageDAONeo4J().ImageFileToByteArray("imageSamples/DCC.TIFF"));
			
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
		
		neo4jConnection.disconnect();

	}
	
	@Test
	public void readImageTest() throws SQLException {
		
		MyImage myImage = null;
		
		int imageId = 1;
		
		String query = "MATCH (n:MyImage { imageId : '"
				+ imageId + "'}) RETURN n";
		
		Neo4JConnection neo4jConnection = new Neo4JConnection();

		neo4jConnection.connect();

		ResultSet resultSet = neo4jConnection.executeQuery(query);
		
		if(resultSet.next()) {
			
			String node = resultSet.getString("n");
			
			System.out.println(node);
			
		}
		
		
	}

}