package br.edu.ifsp.postgresql.controller;

import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.view.JFMainViewNeo4J;

public class MyImageController {

	private MyImage myImage;
	private JFMainViewNeo4J mainView;

	public MyImageController(MyImage myImage, JFMainViewNeo4J mainView) {

	}

	public MyImage getMyImage() {
		return myImage;
	}

	public void setMyImage(MyImage myImage) {
		this.myImage = myImage;
	}

	public JFMainViewNeo4J getMainView() {
		return mainView;
	}

	public void setMainView(JFMainViewNeo4J mainView) {
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
