package br.edu.ifsp.postgresql.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.connection.postgresql.PostgreJDBCConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;

public class UpdateMyImageDAOTest {

	@Test
	public void UpdateMyImagetest() throws SQLException {

		MyImageDAO myImageDAO = new MyImageDAO(new PostgreJDBCConnection());

		MyImage myImage = new MyImage();

		myImage.setImageId(5);

		myImage.setImageName("ECC.TIFF");

		try {

			myImage.setImageBytes(myImageDAO.ImageFileToByteArray("imageSamples/DCC.TIFF"));

			System.out.println(myImage.getImageBytes().length);

		} catch (IOException e) {

			e.printStackTrace();

		}

		myImageDAO.insert(myImage);

		myImage = myImageDAO.search(myImage.getImageId());

		assertEquals("ECC.TIFF", myImage.getImageName());

		myImage.setImageName("DCC.TIFF");

		assertTrue(myImageDAO.update(myImage));
		
		myImage = myImageDAO.search(myImage.getImageId());

		assertEquals("DCC.TIFF", myImage.getImageName());


	}

}
