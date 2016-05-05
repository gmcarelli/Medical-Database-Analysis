package br.edu.ifsp.postgresql.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ReadFromFileDAO;
import br.edu.ifsp.helper.QueryHelper;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.postgresql.connection.PostgreConnection;

public class MyImageDAOPostgre implements ReadFromFileDAO, IDAO<MyImage> {

	private PostgreConnection postgreConnection;
	private ResultSet resultSet;
	private String query;

	public MyImageDAOPostgre() {

		this.postgreConnection = new PostgreConnection();
		this.resultSet = null;
		this.query = null;

	}

	public byte[] ImageFileToByteArray(String imageUrl) throws IOException {

		File file = new File(imageUrl);

		return Files.readAllBytes(file.toPath());

	}

	@Override
	public boolean insert(MyImage myImage) throws SQLException {

		boolean executeUpdate = false;

		this.query = "INSERT INTO image (imageName, imageBytes) VALUES ('" + myImage.getImageName() + "', '"
				+ myImage.getImageBytes() + "');";

		this.postgreConnection.connect();

		executeUpdate = this.postgreConnection.executeUpdate(this.query);

		this.postgreConnection.disconnect();

		return executeUpdate;

	}

	@Override
	public boolean update(MyImage myImage) throws SQLException {

		boolean executeUpdate = false;

		this.query = "UPDATE myImage SET imageName = '" + myImage.getImageName() + "', imageBytes = '"
				+ myImage.getImageBytes() + "' WHERE imageId = " + myImage.getImageId() + ";";

		this.postgreConnection.connect();

		executeUpdate = this.postgreConnection.executeUpdate(this.query);

		this.postgreConnection.disconnect();

		return executeUpdate;

	}

	@Override
	public boolean delete(MyImage myImage) throws SQLException {

		boolean executeUpdate = false;

		this.query = "DELETE FROM myImage WHERE imageId = " + myImage.getImageId() + ";";

		this.postgreConnection.connect();

		executeUpdate = this.postgreConnection.executeUpdate(this.query);

		this.postgreConnection.disconnect();

		return executeUpdate;

	}

	@Override
	public MyImage search(int imageId) throws SQLException {

		this.query = "SELECT * FROM image WHERE imageID = " + imageId + ";";
		
		this.postgreConnection.connect();

		ResultSet resultSet = this.postgreConnection.executeQuery(this.query);

		MyImage myImage = null;

		if (resultSet.next()) {

			myImage = new MyImage(imageId, resultSet.getString("imageName"), resultSet.getBytes("imageBytes"));

		}

		this.postgreConnection.disconnect();

		return myImage;
	}

	@Override
	public List<MyImage> list() throws SQLException {

		this.query = "SELECT * FROM image";

		this.postgreConnection.connect();

		this.resultSet = this.postgreConnection.executeQuery(this.query);

		List<MyImage> myImageList = new ArrayList<>();

		while (this.resultSet.next()) {

			myImageList.add(new MyImage(this.resultSet.getInt("imageId"), this.resultSet.getString("imageName"),
					this.resultSet.getBytes("imageBytes")));

		}

		this.postgreConnection.disconnect();
		
		return myImageList;

	}

	public int getUltimoIdCadastrado(String tabela, String primaryKeyColumn) throws SQLException {

		this.query = "SELECT " + primaryKeyColumn + " FROM " + tabela + " ORDER BY " + primaryKeyColumn + " DESC LIMIT 1";

		this.postgreConnection.connect();

		this.resultSet = this.postgreConnection.executeQuery(this.query);

		int ultimoIdCadastrado = 0;

		if (resultSet.next()) {
			ultimoIdCadastrado = resultSet.getInt(primaryKeyColumn);
		}
		
		this.postgreConnection.disconnect();

		return ultimoIdCadastrado;
		
	}
}
