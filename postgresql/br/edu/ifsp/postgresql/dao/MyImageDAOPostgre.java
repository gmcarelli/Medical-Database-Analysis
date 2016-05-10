package br.edu.ifsp.postgresql.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ReadFromFileDAO;
import br.edu.ifsp.dao.WriteToFileDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.postgresql.connection.PostgreConnection;

public class MyImageDAOPostgre implements ReadFromFileDAO, WriteToFileDAO, IDAO<MyImage> {

	private PostgreConnection postgreConnection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String query;

	public MyImageDAOPostgre() {

		this.postgreConnection = new PostgreConnection();
		this.preparedStatement = null;
		this.resultSet = null;
		this.query = null;

	}

	@Override
	public byte[] ImageFileToByteArray(String imageUrl) throws IOException {

		File file = new File(imageUrl);

		return Files.readAllBytes(file.toPath());

	}
	
	@Override
	public boolean byteArrayToTiffFile(MyImage myImage) throws IOException {

		boolean writeToFile = false;

		FileOutputStream stream = new FileOutputStream("imageOutput/" + myImage.getImageName());

		try {

			stream.write(myImage.getImageBytes());

		} finally {

			stream.close();

			writeToFile = true;

		}

		return writeToFile;

	}

	@Override
	public boolean insert(MyImage myImage) throws SQLException {

		boolean executeUpdate = false;

		this.query = "INSERT INTO image (imageName, imageBytes) VALUES (?, ?)";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setString(1, myImage.getImageName());
		this.preparedStatement.setBytes(2, myImage.getImageBytes());

		executeUpdate = this.postgreConnection.executeUpdate(preparedStatement);

		this.postgreConnection.disconnect();

		return executeUpdate;

	}

	@Override
	public boolean update(MyImage myImage) throws SQLException {

		boolean executeUpdate = false;

		this.query = "UPDATE myImage SET imageName = ?, imageBytes = ? " + "WHERE imageId = ?";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setString(1, myImage.getImageName());
		this.preparedStatement.setBytes(2, myImage.getImageBytes());
		this.preparedStatement.setInt(3, myImage.getImageId());

		executeUpdate = this.postgreConnection.executeUpdate(preparedStatement);

		this.postgreConnection.disconnect();

		return executeUpdate;

	}

	@Override
	public boolean delete(MyImage myImage) throws SQLException {

		boolean executeUpdate = false;

		this.query = "DELETE FROM myImage WHERE imageId = ?";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setInt(1, myImage.getImageId());

		executeUpdate = this.postgreConnection.executeUpdate(preparedStatement);

		this.postgreConnection.disconnect();

		return executeUpdate;

	}

	@Override
	public MyImage search(int imageId) throws SQLException {

		this.query = "SELECT * FROM image WHERE imageID = ?";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setInt(1, imageId);

		ResultSet resultSet = this.postgreConnection.executeQuery(preparedStatement);

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

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.resultSet = this.postgreConnection.executeQuery(preparedStatement);

		List<MyImage> myImageList = new ArrayList<>();

		while (this.resultSet.next()) {

			myImageList.add(new MyImage(this.resultSet.getInt("imageId"), this.resultSet.getString("imageName"),
					this.resultSet.getBytes("imageBytes")));

		}

		this.postgreConnection.disconnect();

		return myImageList;

	}

	public int getUltimoIdCadastrado(String tabela, String primaryKeyColumn) throws SQLException {

		this.query = "SELECT " + primaryKeyColumn + " FROM " + tabela + " ORDER BY " + primaryKeyColumn
				+ " DESC LIMIT 1";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.resultSet = this.postgreConnection.executeQuery(preparedStatement);

		int ultimoIdCadastrado = 0;

		if (resultSet.next()) {
			ultimoIdCadastrado = resultSet.getInt(primaryKeyColumn);
		}

		this.postgreConnection.disconnect();

		return ultimoIdCadastrado;

	}
}
