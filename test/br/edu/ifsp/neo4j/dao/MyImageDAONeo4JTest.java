package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class MyImageDAONeo4JTest {

	@Test
	public void insertImageTest() throws SQLException, UnsupportedEncodingException {
		
		MyImage myImage = new MyImage();

		myImage.setImageId(1);

		myImage.setImageName("DCC.TIFF");

		try {
			myImage.setImageBytes(DAOManager.myImageDAONeo4J().ImageFileToByteArray("imageSamples/DCC.TIFF"));

			System.out.println(myImage.getImageBytes().length);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		String query = "CREATE (n:MyImage { imageId : ?, imageName : ?, imageBytes : ?}) RETURN 1";		
		
		Neo4JConnection neo4jConnection = new Neo4JConnection();

		PreparedStatement preparedStatement = neo4jConnection.connect().prepareStatement(query);
		
		preparedStatement.setInt(1, myImage.getImageId());
		preparedStatement.setString(2, myImage.getImageName());	
		preparedStatement.setString(3, Base64.encodeBase64String(myImage.getImageBytes()));		
		
		assertTrue(neo4jConnection.executeUpdate(preparedStatement));
		
		neo4jConnection.disconnect();

	}	
		
}