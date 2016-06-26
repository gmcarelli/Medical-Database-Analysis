package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.connection.neo4j.Neo4jJDBCConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;

public class ReadImageNeo4jTest {

	@Test
	public void readImageTest() throws SQLException {
		
		MyImageDAO myImageDAO = new MyImageDAO(new Neo4jJDBCConnection());

		MyImage myImage = null;

		int imageId = 1;

		myImage = myImageDAO.search(imageId);
		
		assertTrue(myImage.getImageId() == 1);

		assertTrue(myImage.getImageName().equals("ECC.TIFF"));

		assertTrue(myImage.getImageBytes().length == 11397120
);

	}

}
