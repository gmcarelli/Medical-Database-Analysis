package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class ReadFromDataBaseToFileTest {

	@Test
	public void ReadFromDataBaseToFileTest() throws SQLException {

		MyImage myImage = null;

		int imageId = 1;

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

			System.out.println(imageBytes.length());

			myImage.setImageId(resultSet.getInt("n.imageId"));

			myImage.setImageName(resultSet.getString("n.imageName"));

//			int i = 0;
			
			byte[] imageBytesAux = imageBytes.getBytes();	
			
//			for(int j = 0; j < imageBytes.length(); j++) {
//				imageBytesAux[j] = (byte) imageBytes.charAt(j);
//			}

//			for (byte b : imageBytes.getBytes()) {
//				imageBytesAux[i] = b;
//				i++;				
//			}
			
			myImage.setImageBytes(imageBytesAux);
			
			try {
				assertTrue(DAOManager.myImageDAONeo4J().byteArrayToTiffFile(myImage));
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			System.out.println(myImage.getImageBytes().length);

		}

		assertTrue(myImage.getImageId() == 1);

		assertTrue(myImage.getImageName().equals("DCC.TIFF"));

		assertTrue(myImage.getImageBytes().length == 11487232);

		neo4jConnection.disconnect();
	}

}
