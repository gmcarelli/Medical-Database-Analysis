package br.edu.ifsp.dao;

import br.edu.ifsp.model.MyImage;

public interface WriteToFileDAO {
	
	public abstract boolean byteArrayToTiffFile(MyImage myImage) throws Exception;
	
}

	
	

