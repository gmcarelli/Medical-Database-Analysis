package br.edu.ifsp.postgresql.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.model.MyImage;

public class InsertMyImageDAOPostgre {

	@Test
	public void insertImageTest() throws SQLException {
		MyImage myImage = new MyImage();
		
		myImage.setImageName("DCC.TIFF");
		
		try {
			
			myImage.setImageBytes(DAOManager.myImageDAOPostgre().ImageFileToByteArray("imageSamples/DCC.TIFF"));
			
			assertTrue(DAOManager.myImageDAOPostgre().insert(myImage));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
