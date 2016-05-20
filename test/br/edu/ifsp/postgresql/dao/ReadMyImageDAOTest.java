package br.edu.ifsp.postgresql.dao;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.model.MyImage;

public class ReadMyImageDAOTest {
	
	
	@Test
	public void readImageFromDBTest() throws SQLException {
		
		int imageId = DAOManager.myImageDAOPostgre().getUltimoIdCadastrado("myImage", "imageId");
		
		MyImage myImage = DAOManager.myImageDAOPostgre().search(imageId);			
		
		assertTrue(myImage != null);
		
		assertTrue(myImage.getImageBytes().length == 11487232);	
		
	}
}