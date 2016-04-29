package br.edu.ifsp.neo4j.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ReadFromFileDAO;
import br.edu.ifsp.helper.QueryHelper;
import br.edu.ifsp.model.MyImage;

public class MyImageDAO extends QueryHelper implements ReadFromFileDAO, IDAO<MyImage> {

	public byte[] ImageFileToByteArray(String imageUrl) throws IOException {
		
		File file = new File(imageUrl);

		return Files.readAllBytes(file.toPath());
	}

	@Override
	public boolean insert(MyImage myImage) throws SQLException {
		
		return false;
	}

	@Override
	public boolean update(MyImage myImage) throws SQLException {
		
		return false;
	}

	@Override
	public boolean delete(MyImage myImage) throws SQLException {
		
		return false;
	}

	@Override
	public MyImage search(int imageId) throws SQLException {
		
		return null;
	}

	@Override
	public List<MyImage> list() throws SQLException {
		
		
		return null;
	}

}
