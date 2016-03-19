package br.com.a1402072.mia.testjunitpostgre;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ReadImageTest {	

	@Test
	public void openImageFileTest() {
		File file = new File("imageSamples/DCC.TIFF");
		assertTrue(file != null);		
	} 
	
}
