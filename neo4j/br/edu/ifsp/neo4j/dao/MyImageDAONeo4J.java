package br.edu.ifsp.neo4j.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ReadFromFileDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class MyImageDAONeo4J implements ReadFromFileDAO, IDAO<MyImage> {

	private Neo4JConnection neo4jConnection;
	private ResultSet resultSet;
	private String query;

	public MyImageDAONeo4J() {

		this.neo4jConnection = new Neo4JConnection();
		this.resultSet = null;
		this.query = null;

	}

	public byte[] ImageFileToByteArray(String imageUrl) throws IOException {

		File file = new File(imageUrl);

		return Files.readAllBytes(file.toPath());
	}

	@Override
	public boolean insert(MyImage myImage) throws SQLException {

		boolean executeQuery = false;

		this.query = "CREATE (n:MyImage { imageId : ?, imageName : ?, imageBytes : ? })";

		this.neo4jConnection.setPreparedStatement(this.neo4jConnection.connect().prepareStatement(query));;

		executeQuery = this.neo4jConnection.executeUpdate();

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
		
		this.query = "MATCH (n:MyImage {imageId : '" + imageId + "'}) RETURN n.imageId, n.imageName, n.imageBytes";

		this.neo4jConnection.connect();

		this.resultSet = this.neo4jConnection.executeQuery();

		if (this.resultSet.next()) {
			
			myImage = new MyImage();

			myImage.setImageId(this.resultSet.getInt("n.imageId"));

			myImage.setImageName(this.resultSet.getString("n.imageName"));

			myImage.setImageBytes(this.resultSet.getBytes("n.imageBytes"));
		}

		this.neo4jConnection.disconnect();
		
		return myImage;
		
	}

	@Override
	public List<MyImage> list() throws SQLException {

		return null;
	}

}
