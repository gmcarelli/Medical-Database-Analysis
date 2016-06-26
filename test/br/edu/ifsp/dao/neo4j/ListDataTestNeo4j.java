package br.edu.ifsp.dao.neo4j;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.restlet.engine.io.ReaderInputStream;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.database.neo4j.Neo4jJDBCDatabase;
import br.edu.ifsp.model.MyImage;

public class ListDataTestNeo4j {

	private MyImageDAO myImageDAO;
	
	@Before
	public void beforeTest() throws Exception {
		
		Properties properties = new Properties();
		
		InputStream inputStream = 
			new ReaderInputStream(
				new FileReader(
					new File("resources/neo4j.test.properties")));
		
		properties.load(inputStream);
		
		this.myImageDAO = new MyImageDAO(new Neo4jJDBCDatabase(properties));
	}
	
	@Test
	public void listDataTest() throws Exception {
		
		List<MyImage> myImageList = myImageDAO.listAll();
		
		assertTrue(!myImageList.isEmpty());
		
		assertTrue(myImageList.size() == 2);
		
		assertTrue(myImageList instanceof ArrayList<?>);
		
		assertTrue(myImageList.get(0) != null);
	}
}
