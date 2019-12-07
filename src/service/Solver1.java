package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solver1 {
	String the_solver1 (String sfname) {
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
				masse =  Integer.parseInt(line) ;
				System.out.println("masse " + masse );
				
				// part2
				this_fuel_masse = ( masse / 3 ) - 2  ;
				fuel_masse =  this_fuel_masse;
				System.out.println("this_fuel_masse " + this_fuel_masse );
				//fuel = fuel  + fuel_masse ;
				while (this_fuel_masse >2) {
					this_fuel_masse = ( this_fuel_masse / 3 ) - 2  ;
					System.out.println("additional fuel_masse " + this_fuel_masse );
					if (this_fuel_masse >0 ) {
						fuel_masse = fuel_masse  + this_fuel_masse ;
					}
				}
				
				// part2
				
				// fuel = ( masse / 3 ) - 2  + fuel; 
				System.out.println("fuel_masse  " + fuel_masse );
				tot_fuel_masse = tot_fuel_masse + fuel_masse ;
				System.out.println("tot_fuel_masse  " + tot_fuel_masse );
			}
			
			// fermeture 
			reader.close();
		
		
		
		
		return " done " ;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		throw new IllegalArgumentException("Unable to load " + sfname, e);
		//e.printStackTrace();
	}
	}
}
