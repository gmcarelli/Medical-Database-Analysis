package br.edu.ifsp.control;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.helper.DatabaseManagementSystem;
import br.edu.ifsp.helper.ImageHelper;
import br.edu.ifsp.helper.OperationType;
import br.edu.ifsp.model.MyImage;
import br.edu.ifsp.service.Service;

/**
 * It verifies input parameters and forward correct data to the DAO layer.
 * 
 * @author Lucas Venezian Povoa
 * @since June 25th, 2016
 *
 */
public class Control {

	Service service = new Service();

	public boolean forwardData(String[] args) throws Exception {

		boolean result = false;

		if (!hasValidParam(args))
			help(args);

		else {
			
			DatabaseManagementSystem dbms = DatabaseManagementSystem.getValue(args[0]);

			String databaseName = args[1];
			
			String username = args[2];

			String password = args[3];

			OperationType operationType = OperationType.getValue(args[4]);

			List<MyImage> imagesList = new ArrayList<MyImage>();

			for (int i = 5; i < args.length; i++) {

				MyImage image = new MyImage();
				
				image.setImageId(Integer.parseInt(args[i]));

				if (operationType.equals(OperationType.PERSISTENCE)) {
					image.setImageName(args[++i]);
					image.setImageBytes(ImageHelper.imageFileToByteArray(args[++i]));
				}

				imagesList.add(image);
			}

			result = service.performTest(dbms, username, password, databaseName, operationType, imagesList);
		}
		
		return result;
	}

	/**
	 * Checks input parameters
	 * 
	 * @param args
	 *            is a set of input parameters
	 * @return true if input parameters are valid and false otherwise
	 */
	private boolean hasValidParam(String[] args) {

		boolean result = false;

		if (args.length > 0) {

			result |= args.length >= 6 && args[0].matches("(mongo|neo4j|pgsql)") && args[4].equals("-p")
					&& ((args.length - 5) % 3 == 0);

			result |= args.length >= 6 && args[0].matches("(mongo|neo4j|pgsql)") && args[4].equals("-r");
		}

		return result;
	}

	/**
	 * Prints a help message
	 */
	private String help(String args[]) {

		final String VERSION = "1.0";

		StringBuffer sb = new StringBuffer();

		if (args.length == 1 && args[0].matches("(\\-v|\\-\\-version)"))
			sb.append(System.getProperty("line.separator")).append(VERSION)
					.append(System.getProperty("line.separator"));

		else {

			sb.append("Performance Analysis of Databases for Persistence and Retrievement of Medical Images")
					.append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));

			sb.append("DESCRIPTION").append(System.getProperty("line.separator"))
					.append(System.getProperty("line.separator")).append("Performance analysis of ")
					.append("persistence and retrievement of medical images on ")
					.append("relational and non-relational database systems")
					.append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));

			sb.append("USAGE").append(System.getProperty("line.separator")).append(System.getProperty("line.separator"))
					.append("java -jar medical.jar <database-system> <database-name> <username> <password> <operation-params>")
					.append(System.getProperty("line.separator")).append("   ")
					.append("<database-system> could be \"mongo\", \"neo4j\", or \"pgsql\" (String)")
					.append(System.getProperty("line.separator")).append("   ")
					.append("<database-name> is the schema or collection name (String)")
					.append(System.getProperty("line.separator")).append("   ")
					.append("<username> is the username to access the database system (String)")
					.append(System.getProperty("line.separator")).append("   ")
					.append("<password> is the password to access the database system (String)")
					.append(System.getProperty("line.separator")).append("   ")
					.append("<operation-params> is defined as {-p (<image-id> <image-name> <image-path>)+ | -r (<image-id>)+}")
					.append(System.getProperty("line.separator")).append("   ").append("   ")
					.append("<image-id> is the identification code (exclusive numbers) of the image (Long)")
					.append(System.getProperty("line.separator")).append("   ").append("   ")
					.append("<image-name> is the image name to be persisted (String)")
					.append(System.getProperty("line.separator")).append("   ").append("   ")
					.append("<image-path> is the complete path to access the image to be persisted (TIFF)")
					.append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));

			sb.append("QUESTIONS OR BUG REPORTS").append(System.getProperty("line.separator"))
					.append(System.getProperty("line.separator"))
					.append("https://github.com/gmcarelli/medical-database-analysis/issues")
					.append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));

			sb.append("VERSION").append(System.getProperty("line.separator"))
					.append(System.getProperty("line.separator")).append(VERSION);
		}
		
		System.out.println(sb.toString());
		
		return sb.toString();
	}
}
