package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.*;

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
		
		int imageId = 1;
		
		String query = "MATCH (n:MyImage { imageId : "
				+ imageId + "}) RETURN n.imageId, n.imageName, n.imageBytes";
		
		Neo4JConnection neo4jConnection = new Neo4JConnection();

		neo4jConnection.connect();

		ResultSet resultSet = neo4jConnection.executeQuery(query);
		
		if(resultSet.next()) {
			
			myImage = new MyImage();			
			
			System.out.println(resultSet.getInt("n.imageId"));
			
			System.out.println(resultSet.getString("n.imageName"));
			
			ArrayList<Object> auxList = (ArrayList<Object>) resultSet.getObject("n.imageBytes");			
			
			Object[] test = auxList.toArray();
			
			System.out.println(auxList.size());
			
			myImage.setImageId(resultSet.getInt("n.imageId"));
			
			myImage.setImageName(resultSet.getString("n.imageName"));
			
			myImage.setImageBytes(resultSet.getBytes("n.imageBytes"));					
			
		}
		
		assertTrue(myImage.getImageId() == 1);
		
		assertTrue(myImage.getImageName().equals("DCC.TIFF"));
		
		//assertTrue(myImage.getImageBytes().length == 5);
		
		neo4jConnection.disconnect();
	}

}
