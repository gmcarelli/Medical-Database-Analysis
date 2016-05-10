package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.assertTrue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class ReadImageNeo4jTest {

	@Test
	public void readImageTest() throws SQLException {

		MyImage myImage = null;

		int imageId = 2;

		String query = "MATCH (n:MyImage { imageId : ?}) RETURN n.imageId, n.imageName, n.imageBytes";

		Neo4JConnection neo4jConnection = new Neo4JConnection();

		PreparedStatement preparedStatement = neo4jConnection.connect().prepareStatement(query);
		
		preparedStatement.setInt(1, imageId);

		ResultSet resultSet = neo4jConnection.executeQuery(preparedStatement);

		if (resultSet.next()) {

			myImage = new MyImage();

			System.out.println(resultSet.getInt("n.imageId"));

			System.out.println(resultSet.getString("n.imageName"));

			String imageBytes = resultSet.getString("n.imageBytes");		
			
//			int i = 0;
//			
//			for (byte b : imageBytes.getBytes()) {
//				System.out.print(b + ", ");
//				
//				
//			}
			
//			byte[] test = new byte[imageBytes];
//
//			for (int i = 0; i < imageBytes.size(); i++) {
//
//				test[i] = imageBytes.get(i).byteValue();
//				
//			}

			// test = (byte[]) auxList;

			System.out.println(imageBytes.length());

			myImage.setImageId(resultSet.getInt("n.imageId"));

			myImage.setImageName(resultSet.getString("n.imageName"));

			myImage.setImageBytes(imageBytes.getBytes());

		}

		assertTrue(myImage.getImageId() == 2);

		assertTrue(myImage.getImageName().equals("DCC.TIFF"));

		assertTrue(myImage.getImageBytes().length == 11487232);

		neo4jConnection.disconnect();
	}

}
