package br.edu.ifsp.helper;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.idrsolutions.image.tiff.TiffDecoder;

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

}
