package br.edu.ifsp.neo4j.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4jJDBCConnection;

public class ListDataTestNeo4j {

	@Test
	public void listDataTest() throws SQLException {
		
		MyImageDAO myImageDAO = new MyImageDAO(new Neo4jJDBCConnection());
		
		List<MyImage> myImageList = myImageDAO.list();
		
		assertTrue(!myImageList.isEmpty());
		
		assertTrue(myImageList.size() == 2);
		
		assertTrue(myImageList instanceof ArrayList<?>);
		
		assertTrue(myImageList.get(0) != null);
		
	}

}
