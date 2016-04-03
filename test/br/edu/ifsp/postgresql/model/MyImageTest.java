package br.edu.ifsp.postgresql.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import br.edu.ifsp.model.MyImage;

public class MyImageTest {	
	
	@Test
	public void test() throws IOException {
		String imageUrl = "imageSamples/DCC.TIFF";
		
		byte[] imageBytes = MyImage.fileToByteArray(imageUrl);
		
		System.out.println(imageBytes.length);
		
		assertTrue(imageBytes.length > 0);
	}

}
