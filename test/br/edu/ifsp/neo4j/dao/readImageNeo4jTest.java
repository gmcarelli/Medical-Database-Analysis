package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class readImageNeo4jTest {

	@Test
	public void readImageTest() throws SQLException {
		
		MyImage myImage = null;
		
		int imageId = 5;
		
		String query = "MATCH (n:MyImage { imageId : '"
				+ imageId + "'}) RETURN n.imageId, n.imageName, n.imageBytes";
		
		Neo4JConnection neo4jConnection = new Neo4JConnection();

		neo4jConnection.connect();

		ResultSet resultSet = neo4jConnection.executeQuery(query);
		
		if(resultSet.next()) {
			
			myImage = new MyImage();			
			
			System.out.println(resultSet.getInt("n.imageId"));
			
			System.out.println(resultSet.getString("n.imageName"));
			
			System.out.println(resultSet.getBytes("n.imageBytes"));
			
			myImage.setImageId(resultSet.getInt("n.imageId"));
			
			myImage.setImageName(resultSet.getString("n.imageName"));
			
			myImage.setImageBytes(resultSet.getBytes("n.imageBytes"));
			
			assertTrue(myImage.getImageId() == 1);
			
			assertTrue(myImage.getImageName().equals("DCC.TIFF"));
			
			assertTrue(myImage.getImageBytes().length == 1000);
			
			/*byte[] imageBytes = null;
			
			try {
				 imageBytes = DAOManager.myImageDAONeo4J().ImageFileToByteArray("imageSamples/DCC.TIFF");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i = 0; i < imageBytes.length; i++) {
			
				assertTrue(myImage.getImageBytes()[i] == imageBytes[i]);
			
			}*/
		}
	}

}
