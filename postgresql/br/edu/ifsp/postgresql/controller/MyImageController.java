package br.edu.ifsp.postgresql.controller;

import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.postgresql.view.JFrameMainView;

public class MyImageController {

	private MyImage myImage;
	private JFrameMainView mainView;

	public MyImageController(MyImage myImage, JFrameMainView mainView) {

	}

	public MyImage getMyImage() {
		return myImage;
	}

	public void setMyImage(MyImage myImage) {
		this.myImage = myImage;
	}

	public JFrameMainView getMainView() {
		return mainView;
	}

	public void setMainView(JFrameMainView mainView) {
		this.mainView = mainView;
	}

	public boolean isImageValid() {

		boolean isImageValid = false;
		
		if (this.myImage != null) {

			if (this.myImage.getImageName() == null || this.myImage.getImageName().equals("")) {
				isImageValid =  false;
			}

			if (this.myImage.getImageBytes() == null || this.myImage.getImageBytes().length == 0) {
				isImageValid =  false;
			}

			isImageValid = true;
		}

		return isImageValid;

	}

}
