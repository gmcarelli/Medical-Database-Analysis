package br.edu.ifsp.model;

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
	
}
