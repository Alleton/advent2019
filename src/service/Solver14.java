package service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Solver14 {

	String solver14 (String sfname) {
		read_line( sfname) ;
		
		return "solver 14" ;
	}	// end solver14
	

	void read_line( String filename) {
		try {
			String line  ;
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			/* lecture ligne  */
			 
			while ((line = reader.readLine()) != null) {
				line = line.trim() ;
				System.out.println("this line " + line );
			}
			
			// fermeture 
			reader.close();
			System.out.println(" read done "   ) ;
			
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to load " + filename , e);
			//e.printStackTrace();
		}
	} // read line
}
