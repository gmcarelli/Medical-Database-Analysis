package br.edu.ifsp.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import br.edu.ifsp.model.MyImage;

public abstract class ImageFileDAO implements ReadFromFileDAO, WriteToFileDAO {
	 
	
	public byte[] ImageFileToByteArray(String imageUrl) throws IOException {

		File file = new File(imageUrl);

		return Files.readAllBytes(file.toPath());

	}
	
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

}
