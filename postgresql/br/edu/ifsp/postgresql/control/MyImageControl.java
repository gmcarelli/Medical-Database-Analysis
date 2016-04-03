package br.edu.ifsp.postgresql.control;

import br.edu.ifsp.model.MyImage;

public class MyImageControl {
	
	public String validateMyImage(MyImage myImage) {
		
		if(myImage.getImageName() == null && myImage.getImageName().equalsIgnoreCase("")) {
			return "O nome da imagem deve ser informado";
		}
		
		if(!myImage.getImageName().contains(".")) {
			return "É preciso informar a extensão do arquivo de imagem.";
		}
		
		if(myImage.getImageBytes().length == 0) {
			return "O tamanho da imagem é de 0(zero) bytes. Imagem inválida";
		}
		
		return "Imagem é válida.";
	}

}
