package br.com.a1402072.projetoic.testjunit;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import br.com.a1402072.projetoic.model.MyImage;

public class ImageToByteArrayTest {	
	
	
	@Test
	public void test() throws IOException {
		String imageUrl = "imageSamples/DCC.TIFF";
		
		byte[] imageBytes = MyImage.ImageToByteArray(imageUrl);
		
		System.out.println(imageBytes.length);
		
		assertTrue(imageBytes.length > 0);
		
	}

}
