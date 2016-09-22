package br.edu.ifsp;

import br.edu.ifsp.control.Control;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			Control control = new Control();		
			
			boolean aux =  control.forwardData(args);
			
			if (aux) {
				
				System.out.println("success!");
				
			} else {
				
				System.out.println("fail!");
				
			}
		
		} catch (Exception e) {

			e.printStackTrace();
		}
	}	
}
