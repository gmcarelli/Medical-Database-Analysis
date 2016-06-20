package br.edu.ifsp;

import java.io.IOException;
import java.sql.SQLException;

import br.edu.ifsp.connection.MongodbConnection;
import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.neo4j.connection.Neo4jJDBCConnection;
import br.edu.ifsp.postgresql.connection.PostgreJDBCConnection;

public class Main {

	public static void main(String[] args) {

		MyImage myImage = null;
		MyImageDAO myImageDAO = null;
		int imageId = 0;

		/*
		 * Recuperação de dados
		 */
		if (args.length == 3 && args[0].equals("-r")) {

			try {

				imageId = Integer.parseInt(args[1]);

			} catch (Exception e) {

				System.out.println("O parâmetro imageId deve receber um número inteiro maior que zero.");

			}

			if (imageId > 0) {

				if (args[2].equalsIgnoreCase("postgre")) {

					myImageDAO = new MyImageDAO(new PostgreJDBCConnection());

				} else if (args[2].equalsIgnoreCase("neo4j")) {

					myImageDAO = new MyImageDAO(new Neo4jJDBCConnection());

				} else if (args[2].equalsIgnoreCase("mongodb")) {

					myImageDAO = new MyImageDAO(new MongodbConnection());

				} else {

					System.out.println("Não há suporte para o banco de dados escolhido.");
					
					help();

				}

				if (myImageDAO != null) {

					try {

						myImage = myImageDAO.search(imageId);

						if (myImage != null) {

							System.out.println("Imagem recupeada com sucesso!\nFim do comando");

						} else {

							System.out.println("Imagem não encontrada!\nFim do comando");

						}

					} catch (SQLException e) {

						System.out.println(e.getMessage());

					}

				}

			} else {

				help();

			}

		}

		/*
		 * Inserção de dados
		 */
		else if (args.length == 5 && args[0].equals("-p")) {

			try {

				imageId = Integer.parseInt(args[1]);

			} catch (Exception e) {

				System.out.println("O parâmetro imageId deve receber um número inteiro maior que zero.");
				
			}

			if (imageId > 0) {

				if (args[4].equalsIgnoreCase("postgre")) {

					myImageDAO = new MyImageDAO(new PostgreJDBCConnection());

				} else if (args[4].equalsIgnoreCase("neo4j")) {

					myImageDAO = new MyImageDAO(new Neo4jJDBCConnection());

				} else if (args[4].equalsIgnoreCase("mongodb")) {

					myImageDAO = new MyImageDAO(new MongodbConnection());

				} else {

					System.out.println("Não há suporte para o banco de dados escolhido.");
					
					help();

				}

				if (myImageDAO != null) {

					byte[] imageBytes;

					myImage = new MyImage();

					myImage.setImageId(imageId);

					myImage.setImageName(args[2]);

					try {

						imageBytes = myImageDAO.ImageFileToByteArray(args[3]);

						myImage.setImageBytes(imageBytes);

						boolean insert = myImageDAO.insert(myImage);

						if (insert) {

							System.out.println("Imagem inserida com sucesso!\nFim do comando");

						} else {

							System.out.println("Imagem não inserida!\nFim do comando");

						}

					} catch (IOException e) {

						System.out.println(e.getMessage());

					}

				}

			} else {

				help();

			}

		}
		/*
		 * Parâmetros inválidos
		 */
		else {
			
			help();
			
		}

	}

	public static void help() {

		System.out.println("\nComo utilizar este programa:\n");
		System.out.println("Para inserir uma imagem:\n"
				+ "$>java -jar medical.jar -p <imageId> <nome da imagem> <local da imagem> <nome do banco>\n" );
		System.out.println("Para recuperar uma imagem: $>java -jar medical.jar -r <imageId> <nome do banco>\n");
		System.out.println("Parâmetro aceitos: \n");
		System.out.println("-p - para persistência de uma imagem.");
		System.out.println("-r - para recuperação de uma imagem.");
		System.out.println("<imageId> - o id da imagem a ser manipulada");
		System.out.println("<nome da imagem> - nome da imagem, deve conter também a extensão. Ex: figura1.tiff"
				+ "\nATENÇÃO: o programa espera que a imagem inserida seja do tipo TIFF. "
				+ "\nOutros formatos podem ser usados mas podem causar comportamente indesejado.");
		System.out.println("<local da imagem> - o caminho para o arquivo de imagem.");
		System.out.println("<nome do banco> - nome do banco de dados a ser utilizado."
				+ "\nBancos suportados atualmente: postgre, neo4j e mongodb.\n");
		System.out.println("Medical Database Analysis 1.0"
				+ "\nDúvidas ou sugestões: gmcarelli@gmail.com");
		
	}
}
