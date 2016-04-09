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

	
	
}
