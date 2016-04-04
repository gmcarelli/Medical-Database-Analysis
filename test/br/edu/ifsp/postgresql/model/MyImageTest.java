package br.edu.ifsp.postgresql.model;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import br.edu.ifsp.postgresql.dao.DAOManager;

public class MyImageTest {	
	
	@Test
	public void test() throws IOException {
		String imageUrl = "imageSamples/DCC.TIFF";
		
		byte[] imageBytes = DAOManager.myImageDAO().ImageFileToByteArray(imageUrl);
		
		System.out.println(imageBytes.length);
		
		assertTrue(imageBytes.length > 0);
	}

}
