package br.edu.ifsp.connection;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.MongoClient;

public class MongodbTest {

	@Test
	public void Connectiontest() throws UnknownHostException {
		
		MongoClient mongoCliente = null;
		
		mongoCliente = MongodbConnection.connect();
		
		assertTrue(MongodbConnection.connect() != null);
		
	}
	
	@Test
	public void ConnectionTest2() throws UnknownHostException {
		
		MongoClient mongoClient = new MongoClient();
		
		assertTrue(mongoClient != null);
		
	}

}
