package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class MyImageDAONeo4JTest {

	@Test
	public void insertImageTest() throws SQLException {
		
		MyImage myImage = new MyImage();

		myImage.setImageId(2);

		myImage.setImageName("DCC.TIFF");

		try {
			myImage.setImageBytes(DAOManager.myImageDAONeo4J().ImageFileToByteArray("imageSamples/DCC.TIFF"));

			System.out.println(myImage.getImageBytes().length);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String query = "CREATE (n:MyImage { imageId : "
				+ myImage.getImageId() + ", imageName : '"
				+ myImage.getImageName() + "', imageBytes : [";
		
		System.out.println(myImage.getImageBytes().length);
		
		for(int i= 0; i < 10000; i++) {
			
			query += myImage.getImageBytes()[i] + ", ";
			
			System.out.println(i);
			
		}
		
		query = query.substring(0, query.length() - 2);
		
		query += "] })";
		
		//String query = "CREATE (n:MyImage { imageId : ?, imageName : ?, imageBytes : [?] })";		
		
		Neo4JConnection neo4jConnection = new Neo4JConnection();
		
		neo4jConnection.connect();

//		PreparedStatement preparedStatement = neo4jConnection.connect().prepareStatement(query);
//		
//		preparedStatement.setInt(1, myImage.getImageId());
//		preparedStatement.setString(2, myImage.getImageName());
//		preparedStatement.setBytes(3, myImage.getImageBytes());		
//
//		assertTrue(preparedStatement.executeUpdate() >= 0);
		
		assertTrue(neo4jConnection.executeUpdate(query));
		
		neo4jConnection.disconnect();

	}	
		
}