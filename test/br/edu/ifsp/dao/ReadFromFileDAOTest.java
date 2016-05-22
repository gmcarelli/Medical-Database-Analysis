package br.edu.ifsp.dao;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import br.edu.ifsp.postgresql.connection.PostgreJDBCConnection;

public class ReadFromFileDAOTest {

	@Test
	public void ImageFileToByteArrayTest() throws IOException {
		
		MyImageDAO myImageDAO = new MyImageDAO(new PostgreJDBCConnection());
		
		String imageUrl = "imageSamples/DCC.TIFF";
		
		byte[] imageBytes = myImageDAO.ImageFileToByteArray(imageUrl);		
		
		System.out.println(imageBytes.length);
		
		assertTrue(imageBytes.length > 0);
		
	}
}
