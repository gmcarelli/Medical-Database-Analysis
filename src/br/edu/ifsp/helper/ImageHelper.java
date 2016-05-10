package br.edu.ifsp.helper;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.stream.FileImageOutputStream;

import com.idrsolutions.image.tiff.TiffDecoder;

import br.edu.ifsp.model.MyImage;

public class ImageHelper {

	public static BufferedImage byteArrayToBufferedImage(byte[] imageBytes) throws Exception {
		TiffDecoder decoder = new TiffDecoder(imageBytes);

		return decoder.read();
	}

	public static boolean byteArrayToTiffFile(byte[] imageBytes) throws Exception {

		boolean writeToFile = false;

		FileOutputStream stream = new FileOutputStream("imageOutput/Test.TIFF");

		try {

			stream.write(imageBytes);

		} finally {

			stream.close();

			writeToFile = true;

		}

		return writeToFile;

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

}
