package br.edu.ifsp.queryHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifsp.connection.postgresql.PostgreJDBCConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.helper.QueryHelper;
import br.edu.ifsp.model.MyImage;

public class QueryHelperTest {

	private MyImage myImage;

	@Before
	public void setUp() throws Exception {

		myImage = new MyImage();

		MyImageDAO myImageDAO = new MyImageDAO(new PostgreJDBCConnection());

		myImage.setImageId(1);

		myImage.setImageName("DML.TIFF");

		byte[] imageBytes = null;

		try {
			imageBytes = myImageDAO.ImageFileToByteArray("imageSamples/DML.TIFF");

			System.out.println(imageBytes.length);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		myImage.setImageBytes(imageBytes);

	}

	@Test
	public void setInsertQueryPostgreTest() {

		QueryHelper queryHelper = new QueryHelper();

		Map<String, Object> values = new HashMap<String, Object>();

		values.put("imageId", myImage.getImageId());
		values.put("imageName", myImage.getImageName());
		values.put("imageBytes", myImage.getImageBytes());

		System.out.println(queryHelper.createInsertQueryPostgre("MyImage", values));

	}

	@Test
	public void setInsertQueryNeo4jTest() {

		QueryHelper queryHelper = new QueryHelper();
	
		Map<String, Object> values = new HashMap<String, Object>();

		values.put("imageId", myImage.getImageId());
		values.put("imageName", myImage.getImageName());
		values.put("imageBytes", Base64.encodeBase64String(myImage.getImageBytes()));

		System.out.println(queryHelper.createInsertQueryNeo4j("MyImage", values));

	}

	@Test
	public void createUpdateQueryPostgreTest() {

		QueryHelper queryHelper = new QueryHelper();

		Map<String, Object> values = new HashMap<String, Object>();

		values.put("imageId", myImage.getImageId());
		values.put("imageName", myImage.getImageName());
		values.put("imageBytes", Base64.encodeBase64String(myImage.getImageBytes()));

		System.out.println(queryHelper.createUpdateQueryPostgre("MyImage", values));

	}

	@Test
	public void createUpdateQueryNeo4jTest() {

		QueryHelper queryHelper = new QueryHelper();
		
		Map<String, Object> values = new HashMap<String, Object>();

		values.put("imageId", myImage.getImageId());
		values.put("imageName", myImage.getImageName());
		values.put("imageBytes", Base64.encodeBase64String(myImage.getImageBytes()));

		System.out.println(queryHelper.createUpdateQueryNeo4j("MyImage", values));

	}
}
