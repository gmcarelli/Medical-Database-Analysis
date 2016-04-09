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

public class MyImageDAO extends QueryHelper implements ReadFromFileDAO, IDAO<MyImage> {

	public byte[] ImageFileToByteArray(String imageUrl) throws IOException {
		
		File file = new File(imageUrl);

		return Files.readAllBytes(file.toPath());
	}

	@Override
	public boolean insert(MyImage myImage) throws SQLException {
		
		this.query = "INSERT INTO image (imageName, imageBytes) VALUES (?, ?)";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setString(1, myImage.getImageName());
		this.preparedStatement.setBytes(2, myImage.getImageBytes());

		return this.executeUpdate();
	}

	@Override
	public boolean update(MyImage myImage) throws SQLException {
		
		this.query = "UPDATE myImage SET imageName = ?, imageBytes = ? WHERE imageId = ?";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setString(1, myImage.getImageName());
		this.preparedStatement.setBytes(2, myImage.getImageBytes());
		this.preparedStatement.setInt(3, myImage.getImageId());

		return this.executeUpdate();
	}

	@Override
	public boolean delete(MyImage myImage) throws SQLException {
		
		this.query = "DELETE FROM myImage WHERE imageId = ?";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setInt(1, myImage.getImageId());

		return this.executeUpdate();
	}

	@Override
	public MyImage search(int imageId) throws SQLException {
		
		this.query = "SELECT * FROM image WHERE imageID = ?";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setInt(1, imageId);

		ResultSet resultSet = this.executeQuerySelect();

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

		ResultSet resultSet = this.executeQuerySelect();

		List<MyImage> myImageList = new ArrayList<>();

		while (resultSet.next()) {
			
			myImageList.add(new MyImage(resultSet.getInt("imageId"), resultSet.getString("imageName"),
					resultSet.getBytes("imageBytes")));
			
		}

		this.postgreConnection.disconnect();

		return myImageList;
		
	}

}
