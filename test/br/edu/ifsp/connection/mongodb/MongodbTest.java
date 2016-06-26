package br.edu.ifsp.connection.mongodb;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.MongoClient;

import br.edu.ifsp.connection.AConnection;
import br.edu.ifsp.connection.mongodb.MongodbConnection;

public class MongodbTest {

	@Test
	public void Connectiontest() throws UnknownHostException {
		
		MongoClient mongoClient = new MongoClient();
		
		assertTrue(mongoClient != null);
		
		mongoClient.close();
		
	}
	
	@Test
	public void ConnectionTest2() throws UnknownHostException {
		
		AConnection connection = new MongodbConnection();
			
		assertTrue(connection.connect() != null);
		
	}

}
