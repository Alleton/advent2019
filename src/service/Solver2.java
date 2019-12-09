package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solver2 {
	String the_solver2 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019" );
		String line = null;
		int operation ;
		int pos_operande1 = 0 ;
		int pos_operande2 = 0 ;
		int pos_result    = 0 ;

		int operande1 = 0 ;
		int operande2 = 0 ;
		int resutl    = 0 ;
		int paire = 0 ; 
		System.out.println("filename = " + sfname );
		// part 2 
		// inputs produces the output 19690720

		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			while ((line = reader.readLine()) != null) {
				line = line.trim() ;

				// parseline
				//System.out.println("parseline line  = " + line ) ;


				// let' s do a try
				for ( int j= 0 ; j < 100 ; j = j +1 ) {
					for ( int k = 0 ;  k <100 ; k = k + 1 ) {


						String[] parts =line.split(",");

						parts[1] = String.valueOf(j)  ; 
						parts[2] = String.valueOf(k)  ;

						for ( int i = 0 ; i < parts.length ; i = i + 4 ){
							operation =  Integer.parseInt(parts[i]) ;
							if (operation != 99 ) {
								pos_operande1 =  Integer.parseInt(parts[i + 1 ]) ;
								pos_operande2 =  Integer.parseInt(parts[i + 2 ]) ;
								// part 2 
								
								pos_result    =  Integer.parseInt(parts[i + 3 ]) ;
								operande1 = Integer.parseInt(parts[pos_operande1]) ; 
								operande2 = Integer.parseInt(parts[pos_operande2]) ;
								//System.out.println("operation     = " + operation );
								//System.out.println("pos operande1 = " + pos_operande1 );
								//System.out.println("    operande1 = " + operande1 );

								//System.out.println("pos operande2 = " + pos_operande2 );
								//System.out.println("    operande2 = " + operande2 );
								switch (operation) {
								case 1 :
									resutl = operande1 + operande2  ;
									break ;
								case 2 :
									resutl = operande1 * operande2   ;
									break ; 
								}
								parts[pos_result] = String.valueOf(resutl) ;
								System.out.println("result  = :" + parts[pos_result] + ":" );
															}
							else {
								break ;

							}
						} // end i part.length


						System.out.println("pour j = " + j  + " k =  "  + k + "  result parts(0) = :" + parts[0] + ":" ); 
						if ( Integer.parseInt(parts[0])== 19690720 ) {
							paire = 100  * j + k ; 
						}
						
					} // end k 
				} // end j 

			}  // end while line

			// fermeture 
			reader.close();




			return " done paire = " + paire ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
	}
}

