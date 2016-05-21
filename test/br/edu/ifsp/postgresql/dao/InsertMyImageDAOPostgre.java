package br.edu.ifsp.postgresql.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.postgresql.connection.PostgreJDBCConnection;

public class InsertMyImageDAOPostgre {

	@Test
	public void insertImageTest() throws SQLException {

		MyImageDAO myImageDAO = new MyImageDAO(new PostgreJDBCConnection());

		MyImage myImage = new MyImage();

		myImage.setImageId(1);

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
