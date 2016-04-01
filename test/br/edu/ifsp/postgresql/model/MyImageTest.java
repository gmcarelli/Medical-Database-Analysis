package br.edu.ifsp.postgresql.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import br.edu.ifsp.postgresql.model.MyImage;

public class MyImageTest {	
	
	@Test
	public void test() throws IOException {
		String imageUrl = "imageSamples/DCC.TIFF";
		
		byte[] imageBytes = MyImage.ImageToByteArray(imageUrl);
		
		System.out.println(imageBytes.length);
		
		assertTrue(imageBytes.length > 0);
	}

}
