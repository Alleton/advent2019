package service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solver6 {
	String solver6 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019" );
		String line = null;
		int masse ;
		int fuel = 0 ;
		int this_fuel_masse = 0 ;
		int tot_fuel_masse = 0 ;
		int fuel_masse;
		System.out.println("filename = " + sfname );

		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			while ((line = reader.readLine()) != null) {
				System.out.println("line = " + line );
				
			}


			reader.close();
			return " done " ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
	}
}

