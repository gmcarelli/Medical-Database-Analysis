package br.edu.ifsp.neo4j.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ImageFileDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4jJDBCConnection;

public class MyImageDAONeo4J extends ImageFileDAO implements IDAO<MyImage> {

	private Neo4jJDBCConnection neo4jConnection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String query;

	public MyImageDAONeo4J() {

		this.neo4jConnection = new Neo4jJDBCConnection();
		this.preparedStatement = null;
		this.resultSet = null;
		this.query = null;

	}

	@Override
	public boolean insert(MyImage myImage) throws SQLException {

		boolean executeUpdate = false;

		this.query = "CREATE (n:MyImage { imageId : ?, imageName : ?, imageBytes : ? }) RETURN 1";

		this.preparedStatement = this.neo4jConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setInt(1, myImage.getImageId());
		this.preparedStatement.setString(2, myImage.getImageName());
		this.preparedStatement.setString(3, Base64.encodeBase64String(myImage.getImageBytes()));

		executeUpdate = this.neo4jConnection.executeUpdate(preparedStatement);

		this.neo4jConnection.disconnect();

		return executeUpdate;

	}

	@Override
	public boolean update(MyImage myImage) throws SQLException {

		boolean executeUpdate = false;

		this.query = "MATCH (n:MyImage {imageId : ?}) SET n.imageName = ?, n.imageBytes = ? RETURN 1";

		this.preparedStatement = this.neo4jConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setInt(1, myImage.getImageId());
		this.preparedStatement.setString(2, myImage.getImageName());
		this.preparedStatement.setString(3, Base64.encodeBase64String(myImage.getImageBytes()));

		executeUpdate = this.neo4jConnection.executeUpdate(preparedStatement);

		this.neo4jConnection.disconnect();
		
		return executeUpdate;
		
	}

	@Override
	public boolean delete(MyImage myImage) throws SQLException {

		boolean executeUpdate = false;

		this.query = "MATCH (n:MyImage {imageId : ?}) DETACH DELETE n RETURN 1";

		this.preparedStatement = this.neo4jConnection.connect().prepareStatement(this.query);

		this.preparedStatement.setInt(1, myImage.getImageId());

		executeUpdate = this.neo4jConnection.executeUpdate(preparedStatement);
		
		this.neo4jConnection.disconnect();

		return executeUpdate;
		
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

		List<MyImage> myImageList = new ArrayList<>();
		
		this.query = "MATCH (n:MyImage) RETURN n.imageId, n.imageName, n.imageBytes";

		this.preparedStatement = this.neo4jConnection.connect().prepareStatement(this.query);		

		this.resultSet = this.neo4jConnection.executeQuery(preparedStatement);
		
		MyImage myImage = null;

		while (this.resultSet.next()) {

			myImage = new MyImage();

			myImage.setImageId(this.resultSet.getInt("n.imageId"));

			myImage.setImageName(this.resultSet.getString("n.imageName"));

			myImage.setImageBytes(Base64.decodeBase64(this.resultSet.getString("n.imageBytes")));
			
			myImageList.add(myImage);
			
		}

		this.neo4jConnection.disconnect();

		return myImageList;
		
	}
}
