package br.com.a1402072.projetoic.testjunit;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.com.a1402072.projetoic.control.SystemControl;
import br.com.a1402072.projetoic.model.MyImage;

public class MyImageControlTest {
	
	@Test
	public void insertImageTest() throws SQLException {
		MyImage myImage = new MyImage();
		
		myImage.setImageName("DCC.TIFF");
		try {
			
			myImage.setImageBytes(MyImage.ImageToByteArray("imageSamples/DCC.tif"));
			
			assertTrue(SystemControl.myImageControl().insertMyImageIntoDB(myImage));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void readImageFromDBTest() throws SQLException {
		int imageId = SystemControl.myImageControl().getUltimoIdCadastrado("image", "imageId");
		
		MyImage myImage = SystemControl.myImageControl().readMyImageFromDB(imageId);	
		
		assertTrue(myImage.getImageBytes() != null);
		assertTrue(myImage.getImageBytes().length > 0);	
		
	}
}