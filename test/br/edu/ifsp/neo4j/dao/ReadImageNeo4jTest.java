package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.*;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class ReadImageNeo4jTest {

	@Test
	public void readImageTest() throws SQLException {

		MyImage myImage = null;

		int imageId = 2;

		String query = "MATCH (n:MyImage { imageId : " + imageId + "}) RETURN n.imageId, n.imageName, n.imageBytes";

		Neo4JConnection neo4jConnection = new Neo4JConnection();

		neo4jConnection.connect();

		ResultSet resultSet = neo4jConnection.executeQuery(query);

		if (resultSet.next()) {

			myImage = new MyImage();

			System.out.println(resultSet.getInt("n.imageId"));

			System.out.println(resultSet.getString("n.imageName"));

			ArrayList<Integer> imageBytes = (ArrayList<Integer>) resultSet.getObject("n.imageBytes");		
			
			byte[] test = new byte[imageBytes.size()];

			for (int i = 0; i < imageBytes.size(); i++) {

				test[i] = imageBytes.get(i).byteValue();
				
			}

			// test = (byte[]) auxList;

			System.out.println(imageBytes.size());

			myImage.setImageId(resultSet.getInt("n.imageId"));

			myImage.setImageName(resultSet.getString("n.imageName"));

			myImage.setImageBytes(test);

		}

		assertTrue(myImage.getImageId() == 2);

		assertTrue(myImage.getImageName().equals("DCC.TIFF"));

		assertTrue(myImage.getImageBytes().length == 10000);

		neo4jConnection.disconnect();
	}

}
