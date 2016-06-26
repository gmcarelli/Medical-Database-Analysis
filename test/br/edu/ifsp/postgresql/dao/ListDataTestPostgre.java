package br.edu.ifsp.postgresql.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.edu.ifsp.connection.postgresql.PostgreJDBCConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;

public class ListDataTestPostgre {

	@Test
	public void listDataTest() throws SQLException {
		
		MyImageDAO myImageDAO = new MyImageDAO(new PostgreJDBCConnection());
		
		List<MyImage> myImageList = myImageDAO.list();
		
		assertTrue(!myImageList.isEmpty());
		
		assertTrue(myImageList.size() == 5);
		
		assertTrue(myImageList instanceof ArrayList<?>);
		
		assertTrue(myImageList.get(0) != null);
		
	}

}
