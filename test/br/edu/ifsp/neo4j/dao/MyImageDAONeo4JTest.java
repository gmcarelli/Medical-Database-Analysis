package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
		

		
		String query = "CREATE (n:MyImage { imageId : ?, imageName : ?, imageBytes : ?})";		
		
		Neo4JConnection neo4jConnection = new Neo4JConnection();

		PreparedStatement preparedStatement = neo4jConnection.connect().prepareStatement(query);
		
		preparedStatement.setInt(1, myImage.getImageId());
		preparedStatement.setString(2, myImage.getImageName());
		preparedStatement.setString(3, new String(myImage.getImageBytes()));		
		
//		int i = 0;
//		
//		for (byte b : myImage.getImageBytes()) {
//			System.out.print(b + ", ");
//			
//			if (++i == 100)
//				break;
//		}
		
		assertTrue(neo4jConnection.executeUpdate(preparedStatement));
		
		neo4jConnection.disconnect();

	}	
		
}