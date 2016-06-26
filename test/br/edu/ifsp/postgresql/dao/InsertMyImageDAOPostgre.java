package br.edu.ifsp.postgresql.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.connection.postgresql.PostgreJDBCConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;

public class InsertMyImageDAOPostgre {

	@Test
	public void insertImageTest() throws SQLException {

		MyImageDAO myImageDAO = new MyImageDAO(new PostgreJDBCConnection());

		MyImage myImage = new MyImage();

		myImage.setImageId(6);

		myImage.setImageName("DCC.TIFF");

		try {
			myImage.setImageBytes(myImageDAO.ImageFileToByteArray("imageSamples/DCC.TIFF"));

			System.out.println(myImage.getImageBytes().length);

		} catch (IOException e) {
			e.printStackTrace();
		}

		assertTrue(myImageDAO.insert(myImage));

	}

}
