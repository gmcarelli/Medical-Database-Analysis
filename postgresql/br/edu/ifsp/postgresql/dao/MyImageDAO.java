package br.edu.ifsp.postgresql.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.idrsolutions.image.tiff.TiffDecoder;

import br.edu.ifsp.dao.ReadFromFileDao;
import br.edu.ifsp.helper.QueryHelper;
import br.edu.ifsp.model.MyImage;

public class MyImageDAO extends QueryHelper implements ReadFromFileDao {

	public boolean insertMyImageIntoDB(MyImage myImage) throws SQLException {
		this.query = "INSERT INTO image (imageName, imageBytes) VALUES (?, ?)";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);
		
		this.preparedStatement.setString(1, myImage.getImageName());
		this.preparedStatement.setBytes(2, myImage.getImageBytes());

		return this.executeUpdate();
	}

	public boolean updateMyImageInDB(MyImage myImage) throws SQLException {
		this.query = "UPDATE myImage SET imageName = ?, imageBytes = ? WHERE imageId = ?";

		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setString(1, myImage.getImageName());
		this.preparedStatement.setBytes(2, myImage.getImageBytes());
		this.preparedStatement.setInt(3, myImage.getImageId());

		return this.executeUpdate();
	}

	public boolean deleteMyImageFromDB(MyImage myImage) throws SQLException {
		this.query = "DELETE FROM myImage WHERE imageId = ?";
		
		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);
		
		this.preparedStatement.setInt(1, myImage.getImageId());

		return this.executeUpdate();
	}

	public MyImage readMyImageFromDB(int imageId) throws SQLException {
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
	
	public byte[] fileToByteArray(String imageUrl) throws IOException {
		File file = new File(imageUrl);

		return Files.readAllBytes(file.toPath());
	}
	
}
