package br.com.a1402072.projetoic.model;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.idrsolutions.image.tiff.TiffDecoder;

public class MyImage {

	private int imageId;
	private String imageName;
	private byte[] imageBytes;
	
	public MyImage() {
		
	}

	public MyImage(int imageID, String imageName, byte[] imageBytes) {
		this.imageId = imageID;
		this.imageName = imageName;
		this.imageBytes = imageBytes;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public byte[] getImageBytes() {
		return imageBytes;
	}

	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}

	public static byte[] ImageToByteArray(String imageUrl) throws IOException {
		File file = new File(imageUrl);

		return Files.readAllBytes(file.toPath());
	}

	public static BufferedImage byteArrayToBufferedImage(byte[] imageBytes) throws Exception {
		TiffDecoder decoder = new TiffDecoder(imageBytes);

		return decoder.read();
	}
	
	public static BufferedImage resizeBufferedImage(BufferedImage image, int newWidth, int newHeight) {
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();

		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, image.getType());

		Graphics2D g = resizedImage.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, imageWidth, imageHeight, null);
		
		g.dispose();
		
		return resizedImage;
	}
}
