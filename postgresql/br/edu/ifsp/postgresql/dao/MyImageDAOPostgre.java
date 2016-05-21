package br.edu.ifsp.postgresql.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.postgresql.connection.PostgreJDBCConnection;

public class MyImageDAOPostgre  {

	private String query;
	private PreparedStatement preparedStatement;
	private PostgreJDBCConnection postgreConnection;
	private ResultSet resultSet;

	
	public List<MyImage> list() throws Exception {

		this.query = "SELECT * FROM myImage";

		//this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		//this.resultSet = this.postgreConnection.executeQuery(preparedStatement);

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

	public int getUltimoIdCadastrado(String tabela, String primaryKeyColumn) throws Exception {

		this.query = "SELECT " + primaryKeyColumn + " FROM " + tabela + " ORDER BY " + primaryKeyColumn
				+ " DESC LIMIT 1";

		//this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);

		//this.resultSet = this.postgreConnection.executeQuery(preparedStatement);

		int ultimoIdCadastrado = 0;

		if (resultSet.next()) {
			ultimoIdCadastrado = resultSet.getInt(primaryKeyColumn);
		}

		this.postgreConnection.disconnect();

		return ultimoIdCadastrado;

	}
}
