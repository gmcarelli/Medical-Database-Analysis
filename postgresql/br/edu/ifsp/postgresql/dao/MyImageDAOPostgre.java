package br.edu.ifsp.postgresql.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ImageFileDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.postgresql.connection.PostgreConnection;

public class MyImageDAOPostgre extends ImageFileDAO implements IDAO<MyImage> {

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
	public boolean insert(MyImage myImage) throws SQLException {

		boolean executeUpdate = false;

		this.query = "INSERT INTO myImage (imageName, imageBytes) VALUES (?, ?)";

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

		this.query = "SELECT * FROM myImage WHERE imageId = ?";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setInt(1, imageId);

		this.resultSet = this.postgreConnection.executeQuery(preparedStatement);

		MyImage myImage = null;

		if (this.resultSet.next()) {

			myImage = new MyImage();

			myImage.setImageId(this.resultSet.getInt("imageId"));

			myImage.setImageName(this.resultSet.getString("imageName"));

			myImage.setImageBytes(this.resultSet.getBytes("imageBytes"));

		}

		this.postgreConnection.disconnect();

		return myImage;
	}

	@Override
	public List<MyImage> list() throws SQLException {

		this.query = "SELECT * FROM myImage";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.resultSet = this.postgreConnection.executeQuery(preparedStatement);

		List<MyImage> myImageList = new ArrayList<>();
		
		MyImage myImage = null;

		while (this.resultSet.next()) {
			
			myImage = new MyImage();

			myImage.setImageId(this.resultSet.getInt("imageId"));

			myImage.setImageName(this.resultSet.getString("imageName"));

			myImage.setImageBytes(this.resultSet.getBytes("imageBytes"));

			myImageList.add(myImage);

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
