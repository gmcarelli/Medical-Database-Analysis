package br.edu.ifsp;

public class Main {

	public static void main(String[] args) {
		help();
		
	}

	public static void help() {

		StringBuffer sb = new StringBuffer();
		
		sb.append("Performance Analysis of Databases for Persistence and Retrievement of Medical Images")
		  .append(System.getProperty("line.separator"))
		  .append(System.getProperty("line.separator"));
		
		sb.append("DESCRIPTION")
          .append(System.getProperty("line.separator"))
          .append(System.getProperty("line.separator"))
		  .append("Performance analysis of ")
		  .append("persistence and retrievement of medical images in ")
		  .append("relational and non-relational database systems")
		  .append(System.getProperty("line.separator"))
		  .append(System.getProperty("line.separator"));
		
		sb.append("USAGE")
		  .append(System.getProperty("line.separator"))
		  .append(System.getProperty("line.separator"))
		  .append("java -jar medical.jar <database-system> <database-name> <username> <password> <operation-params>")
		  .append(System.getProperty("line.separator"))
		  .append("   ")
		  .append("<database-system> could be \"mongo\", \"neo4j\", or \"pgsql\" (String)")
		  .append(System.getProperty("line.separator"))
		  .append("   ")
		  .append("<database-name> is the schema or collection name (String)")
		  .append(System.getProperty("line.separator"))
		  .append("   ")
		  .append("<username> is the username to access the database system (String)")
		  .append(System.getProperty("line.separator"))
		  .append("   ")
		  .append("<password> is the password to access the database system (String)")
		  .append(System.getProperty("line.separator"))
		  .append("   ")
		  .append("<operation-params> is defined as {-p (<image-id> <image-name> <image-path>)+ | -r (<image-id>)+}")
		  .append(System.getProperty("line.separator"))
		  .append("   ").append("   ")
		  .append("<image-id> is the identification code (exclusive numbers) of the image (Long)")
		  .append(System.getProperty("line.separator"))
		  .append("   ").append("   ")
		  .append("<image-name> is the image name to be persisted (String)")
		  .append(System.getProperty("line.separator"))
		  .append("   ").append("   ")
		  .append("<image-path> is the complete path to access the image to be persisted (TIFF)")
		  .append(System.getProperty("line.separator"))
		  .append(System.getProperty("line.separator"));
		
		sb.append("QUESTIONS OR BUG REPORTS")
		  .append(System.getProperty("line.separator"))
		  .append(System.getProperty("line.separator"))
		  .append("https://github.com/gmcarelli/medical-database-analysis/issues")
		  .append(System.getProperty("line.separator"))
		  .append(System.getProperty("line.separator"));
		
		sb.append("VERSION")
		  .append(System.getProperty("line.separator"))
		  .append(System.getProperty("line.separator"))
		  .append("1.0");
		
		System.out.println(sb);
		
	}
}
