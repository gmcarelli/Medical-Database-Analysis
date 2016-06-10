package br.edu.ifsp;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * Recuperação de dados
		 */
		if (args.length == 4 && args[0].equals("-r")) {
			
		}
		
		/*
		 * Inserção de dados
		 */
		else if (args.length == 6 && args[0].equals("-p")) {
		
			
			
		}
		/*
		 * Parâmetros inválidos
		 */
		else {
			help();
		}
	
			
		
			
	}
	
	public static void help() {
		
		System.out.println("Como utilizar este programa:");
		System.out.println("Exemplo: java -jar medical.jar -r 1");
	}
}
