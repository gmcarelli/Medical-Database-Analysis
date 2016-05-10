package br.edu.ifsp.neo4j.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ReadFromFileDAO;
import br.edu.ifsp.dao.WriteToFileDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class MyImageDAONeo4J implements ReadFromFileDAO, WriteToFileDAO, IDAO<MyImage> {

	private Neo4JConnection neo4jConnection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String query;

	public MyImageDAONeo4J() {

		this.neo4jConnection = new Neo4JConnection();
		this.preparedStatement = null;
		this.resultSet = null;
		this.query = null;

	}

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

		boolean executeQuery = false;

		this.query = "CREATE (n:MyImage { imageId : ?, imageName : ?, imageBytes : ? })";

		this.preparedStatement = this.neo4jConnection.connect().prepareStatement(this.query);
		
		this.preparedStatement.setInt(1, myImage.getImageId());
		this.preparedStatement.setString(2, myImage.getImageName());
		this.preparedStatement.setString(3, Base64.encodeBase64String(myImage.getImageBytes()));

		executeQuery = this.neo4jConnection.executeUpdate(preparedStatement);

		this.neo4jConnection.disconnect();

		return executeQuery;

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

		MyImage myImage = null;
		
		this.query = "MATCH (n:MyImage {imageId : ?}) RETURN n.imageId, n.imageName, n.imageBytes";

		this.preparedStatement = this.neo4jConnection.connect().prepareStatement(this.query);
		
		this.preparedStatement.setInt(1, imageId);

		this.resultSet = this.neo4jConnection.executeQuery(preparedStatement);

		if (this.resultSet.next()) {
			
			myImage = new MyImage();

			myImage.setImageId(this.resultSet.getInt("n.imageId"));

			myImage.setImageName(this.resultSet.getString("n.imageName"));
			
			myImage.setImageBytes(Base64.decodeBase64(this.resultSet.getString("n.imageBytes")));
		}

		this.neo4jConnection.disconnect();
		
		return myImage;
		
	}

	@Override
	public List<MyImage> list() throws SQLException {

		return null;
	}
	
}
