package br.edu.ifsp.dao;

import java.io.IOException;

public interface ReadFromFileDao {
	
	public byte[] fileToByteArray(String imageUrl) throws IOException;
	
}

	
	

