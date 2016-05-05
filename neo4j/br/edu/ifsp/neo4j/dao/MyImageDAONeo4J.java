package br.edu.ifsp.neo4j.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifsp.connection.Neo4JConnection;
import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ReadFromFileDAO;
import br.edu.ifsp.helper.QueryHelper;
import br.edu.ifsp.model.MyImage;

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
		
		this.query = "CREATE (n:MyImage { imageId : '"
				+ myImage.getImageId() + "', imageName : '"
						+ myImage.getImageName() + "', imageBytes : '"
							+ myImage.getImageBytes() + "' })";
		
		this.neo4jConnection.connect();
		
		executeQuery = this.neo4jConnection.executeUpdate(this.query);
		
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
		
		return null;
	}

	@Override
	public List<MyImage> list() throws SQLException {
		
		
		return null;
	}

}
