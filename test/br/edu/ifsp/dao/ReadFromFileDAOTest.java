package br.edu.ifsp.dao;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ReadFromFileDAOTest {

	@Test
	public void ImageFileToByteArrayTest() throws IOException {
		
		String imageUrl = "imageSamples/DCC.TIFF";
		
		byte[] imageBytes = DAOManager.myImageDAOPostgre().ImageFileToByteArray(imageUrl);
		
		System.out.println(imageBytes.length);
		
		assertTrue(imageBytes.length > 0);
		
	}
}
