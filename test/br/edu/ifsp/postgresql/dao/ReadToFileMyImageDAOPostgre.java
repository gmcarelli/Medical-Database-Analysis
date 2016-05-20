package br.edu.ifsp.postgresql.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.model.MyImage;

public class ReadToFileMyImageDAOPostgre {

	@Test
	public void ReadToFileTest() throws SQLException {
		
		int imageId = DAOManager.myImageDAOPostgre().getUltimoIdCadastrado("myImage", "imageId");

		MyImage myImage = DAOManager.myImageDAOPostgre().search(imageId);

		try {
			assertTrue(DAOManager.myImageDAOPostgre().byteArrayToTiffFile(myImage));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
