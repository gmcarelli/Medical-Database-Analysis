package br.edu.ifsp.helper;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import com.idrsolutions.image.tiff.TiffDecoder;

import br.edu.ifsp.model.MyImage;

public class ImageHelper {	
	
	public static BufferedImage byteArrayToBufferedImage(byte[] imageBytes) throws Exception {
		
		TiffDecoder decoder = new TiffDecoder(imageBytes);

		return decoder.read();
	}

	public static BufferedImage resizeBufferedImage(BufferedImage image, int newWidth, int newHeight) {
		
		int imageWidth = image.getWidth();
		
		int imageHeight = image.getHeight();

		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, image.getType());

		Graphics2D g = resizedImage.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, imageWidth, imageHeight, null);

		g.dispose();

		return resizedImage;
	}
	
	public static byte[] imageFileToByteArray(String imageUrl) throws IOException {

		File file = new File(imageUrl);

		return imageFileToByteArray(file);
	}
	
	public static byte[] imageFileToByteArray(File file) throws IOException {
		return Files.readAllBytes(file.toPath());
	}

	public static boolean byteArrayToTiffFile(MyImage myImage) throws IOException {

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
