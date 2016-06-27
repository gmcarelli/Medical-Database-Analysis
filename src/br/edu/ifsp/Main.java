package br.edu.ifsp;

import br.edu.ifsp.control.Control;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			Control control = new Control();		
			
			control.forwardData(args);
		
		} catch (Exception e) {

			e.printStackTrace();
		}
	}	
}
