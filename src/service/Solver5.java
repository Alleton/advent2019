package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



import org.apache.commons.lang3.StringUtils;

// import Str

public class Solver5 {
	String solver5 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019 Day 5" );
		int my_input = 5; 

		final int POSITION_MODE = 0  ;
		final int IMMEDIATE_MODE = 1  ;
		int mode_of_1 ;
		int mode_of_2 ;
		int mode_of_3 ;
		int op_code_2 ;

		String line = null;
		int operation ;
		int pos_operande1 = 0 ;
		int pos_operande2 = 0 ;
		int pos_operande3 = 0 ;
		int pos_result    = 0 ;

		int operande1 = 0 ;
		int operande2 = 0 ;
		int operande3 = 0 ;
		int resutl    = 0 ;
		int resultat    = 0 ;

		System.out.println("filename = " + sfname );
		// part 2 
		// inputs produces the output 19690720

		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			line = reader.readLine() ; 
			// fermeture 
			reader.close();
			System.out.println(" read done "   ) ;
			line = line.trim() ;
			int pointeur = 0 ;			// pointeur programme
			// parseline
			//System.out.println("parseline line  = " + line ) ;
			String[] parts =line.split(",");
			// parse instruction

			while ( pointeur < parts.length) {

				operation =  Integer.parseInt(parts[pointeur]) ;

				// opcode complet
				String opcode = parts[pointeur];
				System.out.println(" opcode =   " + opcode  ) ;
				//String opcode_complet = StringUtils.leftPad("123456", 10, "0") ; 
				String opcode_complet;
				opcode_complet = StringUtils.leftPad(opcode, 5 , "0") ;
				System.out.println(" opcode_complet =   " + opcode_complet  ) ;
				if ( operation == 0   ) { Runtime.getRuntime().halt (0) ; }
				
				mode_of_1 = Integer.parseInt(StringUtils.substring(opcode_complet, 2, 3) ) ;
				mode_of_2 = Integer.parseInt(StringUtils.substring(opcode_complet, 1, 2 ) ) ;
				mode_of_3 = Integer.parseInt(StringUtils.substring(opcode_complet, 0, 1) ) ;
				op_code_2    = Integer.parseInt(StringUtils.substring(opcode_complet, 3, 5) )    ;
				System.out.println(" mode_of_1 =   " + mode_of_1  ) ;
				System.out.println(" mode_of_2 =   " + mode_of_2  ) ;
				System.out.println(" mode_of_3 =   " + mode_of_3  ) ;
				System.out.println(" op_code_2 =   " + op_code_2  ) ; 

				
//
//				//  troisieme terme
//				if (mode_of_3 == POSITION_MODE) {
//					pos_operande3 = Integer.parseInt(parts[pointeur + 3 ]) ;
//					operande3 = Integer.parseInt(parts[pos_operande3] ) ;
//
//				} else {
//					operande3 = Integer.parseInt(parts[pointeur + 3] ) ;
//				}



				// le micro code
				switch (op_code_2 ) {
				case 1 :
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						System.out.println(" pos_operande1 =   " + pos_operande1  ) ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;
					}

					System.out.println(" addition  "  + parts[pos_operande1 ] + " " + parts[pos_operande2 ] ) ; 
					resutl = ( operande1   + operande2   )   ;
					pos_result = Integer.parseInt(parts[pointeur + 3 ]) ;
					parts[pos_result] = String.valueOf(resutl) ;
					pointeur = pointeur + 4 ;
					//
//					Integer.parseInt(parts[pos_operande1])  == Integer.parseInt(parts[pos_operande2]) ) {
//						pointeur = Integer.parseInt(parts[pointeur + 3])  ;
	
					System.out.println(" result "  + resutl ) ; 

					break ;
				case 2 :
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						System.out.println(" pos_operande1 =   " + pos_operande1  ) ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;
					}
					System.out.println(" addition  "  + parts[pos_operande1 ] + " " + parts[pos_operande2 ] ) ; 
					resutl = ( operande1   * operande2   )   ;
					System.out.println(" result "  + resutl ) ;
					pos_result = Integer.parseInt(parts[pointeur + 3 ]) ;
					parts[pos_result] = String.valueOf(resutl) ;
					pointeur = pointeur + 4 ;

					break ;
				case 3 :
					System.out.println(" opcode =   " + operation  + " input value ") ;
					pointeur = pointeur  + 1 ;
					pos_result    =  Integer.parseInt(parts[pointeur]) ;
					parts[pos_result] = String.valueOf(my_input) ;
					pointeur = pointeur  + 1 ;
					System.out.println(" opcode 3 pos_result  =   " + pos_result  + "  valeur  " + parts[pos_result] ) ;
					break ;
				case 4 :
					System.out.println(" opcode =   " + operation   + " ouput ") ;
					if (mode_of_1 == POSITION_MODE) {
						pos_result = Integer.parseInt(parts[pointeur +1  ]) ;
						System.out.println(" pos_result =   " + pos_result + " joutput : "  ) ;
						
					} else {
						pos_result    =  pointeur +1  ;
						System.out.println(" pos_result     =   " + pos_result  ) ;
					}

					
					resutl = Integer.parseInt(parts[pos_result] );
					System.out.println(" result "  + parts[pos_result] ) ;
					resultat = resutl ;
					
					pointeur = pointeur  + 2 ;

					break ;
				case 5 :
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						System.out.println(" pos_operande1 =   " + pos_operande1 + " jump-if-true: "  ) ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;
					}
					System.out.println(" opcode =   " + operation  ) ;
					System.out.println(" jump if true =   " + operation  ) ;
					
					if (     operande1  != 0 ) {
						pointeur = operande2   ;
					} else {
						pointeur = pointeur  + 3 ;
					}
					System.out.println(" new pointeur =  "  + pointeur ) ;
					break ;
				case 6 :
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						System.out.println(" pos_operande1 =   " + pos_operande1   + " jump-if-false: ") ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;
					}
					System.out.println(" opcode =   " + operation  ) ;
					System.out.println(" jump if false =   " + operation  ) ;
					if (      operande1  == 0 ) {
						pointeur = operande2   ;
					} else {
						pointeur = pointeur  + 3 ;
					}
					System.out.println(" new pointeur =  "  + pointeur ) ;
					break ;
				case 7 :
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						System.out.println(" pos_operande1 =   " + pos_operande1 + " less than " ) ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;
					}
					pos_result =  Integer.parseInt(parts[pointeur + 3 ]) ;
					
					
					System.out.println(" opcode =   " + operation  ) ;
					System.out.println("  less =   " + operation  ) ;
					// pointeur = pointeur  + 1 ;


					if (      operande1  < operande2 ) {
						parts[pos_result] = String.valueOf(1) ;
						System.out.println("  less =  pos_result = " + pos_result + " 1"  ) ;
					} else {
						parts[pos_result] = String.valueOf(0) ;
						System.out.println("  less =  pos_result = " + pos_result + " 0"  ) ;
					}
					pointeur = pointeur + 4 ;
					System.out.println(" new pointeur =  "  + pointeur ) ;
					break ;
				case 8 :
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						System.out.println(" pos_operande1 =   " + pos_operande1  +  " equals" ) ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						System.out.println(" operande2     =   " + operande2  ) ;
					}
					
					pos_result =  Integer.parseInt(parts[pointeur + 3 ]) ;
					
					System.out.println(" opcode =   " + operation  ) ;
					System.out.println(" equals =   " + operation  ) ;
					// pointeur = pointeur  + 1 ;
					if (      operande1  == operande2 ) {
						parts[pos_result] = String.valueOf(1) ;
					} else {
						parts[pos_result] = String.valueOf(0) ;
					}
					pointeur = pointeur + 4 ;				
					System.out.println(" new pointeur =  "  + pointeur ) ;

					break ;
				case 99 :
					System.out.println(" opcode =   " + operation  ) ;
					System.out.println(" fin  "  ) ;
					System.out.println(" resultat =   " + resultat  ) ;
					// halt this process
					Runtime.getRuntime().halt(0);
					break ;
				}

				
			}
			return " done solver  5 = " + resultat ;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
	}
}

