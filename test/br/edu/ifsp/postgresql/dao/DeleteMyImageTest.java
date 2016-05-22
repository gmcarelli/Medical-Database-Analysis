package br.edu.ifsp.postgresql.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.postgresql.connection.PostgreJDBCConnection;

public class DeleteMyImageTest {

	@Test
	public void deleteMyImagetest() throws SQLException {
		
		MyImageDAO myImageDAO = new MyImageDAO(new PostgreJDBCConnection());

		MyImage myImage = new MyImage();

		myImage.setImageId(6);

		myImage.setImageName("ECC.TIFF");

		try {

			myImage.setImageBytes(myImageDAO.ImageFileToByteArray("imageSamples/DCC.TIFF"));

			System.out.println(myImage.getImageBytes().length);

		} catch (IOException e) {

			e.printStackTrace();

		}

		myImageDAO.insert(myImage);
		
		assertTrue(myImageDAO.delete(myImage.getImageId()));
		
		assertEquals(myImageDAO.search(myImage.getImageId()), null);
		
	}
}
