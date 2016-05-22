package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4jJDBCConnection;

public class ReadImageNeo4jTest {

	@Test
	public void readImageTest() throws SQLException {
		
		MyImageDAO myImageDAO = new MyImageDAO(new Neo4jJDBCConnection());

		MyImage myImage = null;

		int imageId = 1;

		myImage = myImageDAO.search(imageId);
		
		assertTrue(myImage.getImageId() == 1);

		assertTrue(myImage.getImageName().equals("DCC.TIFF"));

		assertTrue(myImage.getImageBytes().length == 11487232);

	}

}
