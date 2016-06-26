package br.edu.ifsp.postgresql.dao;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.connection.postgresql.PostgreJDBCConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;

public class ReadMyImageDAOTest {
	
	
	@Test
	public void readImageFromDBTest() throws SQLException {
		
		MyImageDAO myImageDAO = new MyImageDAO(new PostgreJDBCConnection());
		
		PostgreJDBCConnection connection = new PostgreJDBCConnection();
		
		connection.connect();
		
		int imageId = connection.getLastInsertedId("MyImage", "imageId");
				
		MyImage myImage = myImageDAO.search(imageId);			
		
		assertTrue(myImage != null);
		
		assertTrue(myImage.getImageBytes().length == 11487232);	
		
	}
}