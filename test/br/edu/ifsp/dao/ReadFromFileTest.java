package br.edu.ifsp.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.edu.ifsp.helper.ImageHelper;

public class ReadFromFileTest {

	@Test
	public void imageFileToByteArrayTest() throws Exception {
		
		String imageUrl = "imageSamples/DCC.TIFF";
		
		byte[] imageBytes = ImageHelper.imageFileToByteArray(imageUrl);		
		
		assertTrue(imageBytes.length > 0);
	}
}
