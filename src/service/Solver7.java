package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import model.S7_amplifier;  // la classe 


public class Solver7 {
	
	final  int THRUSTERS = 99 ; 

	//String[] parts String[1000];
	Vector instruction =new Vector();
	int max_thruster = 0 ;					// notre but 
	int thruster = 0 ;					// notre but
	
	String line = null;
	long res_amplifier ; 
	String temp ;
	int nb_instruction ; 
	int phase_setting ;		// permier input
	int input ;		// deuxieme input
	int inputs[]  = new int[2]; // combining both statements in one
	int input_number = 0 ;   	// pour lire les 2 inputs

	//int phase_settings [] = new int []  {0,1,2,3,4} ; // 9,8,7,6,5
	int phase_settings [] = new int [] {9, 7, 6, 5, 8} ; //  {5,6,7,8,9} ; // 9,8,7,6,5

	// int test_phase_settings [] = new int [] { 0,1,2,3,4} ;
	
	 void permute (int[]  intArray, int start) {
	    for(int i = start; i < intArray.length; i++){
	        int temp = intArray[start];
	        intArray[start] = intArray[i];
	        intArray[i] = temp;
	        permute(intArray, start + 1);
	        intArray[i] = intArray[start];
	        intArray[start] = temp;
	    }
	    if (start == intArray.length - 1) {
	        // System.out.println(java.util.Arrays.toString(intArray));
	        // appel solver7_amplificateur
	    	///////////////////thruster = solver7_amplificateur ();
	    	System.out.println(Arrays.toString(intArray));
	    	thruster = solver7_amplificateur ();
	    	// System.out.println("retour   solver7_amplificateur   " + thruster ) ;
	    	
	    	
	    	if ( max_thruster < thruster ) {
	    		max_thruster = thruster  ;
	    		
	    	}
	    } // end start == intArray.length - 1
//	    max_thruster = solver7_amplificateur ();

	    //		 max_thruster = solver7_amplificateur ();
	}  // end permute

	
	
	String solver7 (String sfname) {
		//int thruster = 0;
		// lecture pb
		read_line(  sfname) ; 		// line contient les donnees 
		
 		// genere les permutations
		permute(phase_settings, 0);
	//			if ( max_thruster < Integer.parseInt(solver7_amplificateur ( )) ) max_thruster = Integer.parseInt(solver7_amplificateur ( )) ;
		//return (solver7_amplificateur ( )) ;
		return String.valueOf(max_thruster );
	}
	// 		if ( max_thruster < Integer.parseInt(res_amplifier) ) max_thruster = Integer.parseInt(res_amplifier) ;
	

	int solver7_amplificateur () {
		// 	public S7_amplifier ( int  nom  ,int sortie ,  String i_line , int i_phase_setting  , int i_input  ){
		// ampli A
		S7_amplifier ampliA = new S7_amplifier ( 0 ,1 , line ,  phase_settings [0] , 0 ) ;
		res_amplifier = ampliA.start() ;
		
		System.out.println(" fin ampli A      =   " + res_amplifier  ) ;
		
		// ampli B
		S7_amplifier ampliB = new S7_amplifier ( 1 ,2 , line ,  phase_settings [1] , res_amplifier ) ;
		System.out.println( " ");
		System.out.println( "");
		System.out.println( " ******************* ");
		System.out.println( "ampliB = new S7_amplifier ");
		System.out.println( "");
		
System.out.println( "ampliB = new S7_amplifier  name     " + ampliB.getAmplifier_number());
System.out.println( "ampliB = new S7_amplifier  pointeur " + ampliB.getAmplifier_pointeur());
		res_amplifier = ampliB.start() ;
//mmmm
		 // Runtime.getRuntime().halt (0) ;
		// ampli C
		S7_amplifier ampliC = new S7_amplifier ( 2 , 3 , line ,  phase_settings [2] , res_amplifier ) ;
		res_amplifier = ampliC.start() ;

		// ampli D
		S7_amplifier ampliD = new S7_amplifier ( 3 , 4 , line ,  phase_settings [3] , res_amplifier ) ;
		res_amplifier = ampliD.start() ;

		// ampli E
		S7_amplifier ampliE = new S7_amplifier ( 4 , 0  , line ,  phase_settings [4] , res_amplifier ) ;
		res_amplifier = ampliE.start() ;

		int loop = 0 ;
		
		while ( ampliE.getAmplifier_state() != THRUSTERS ) {
			System.out.println("ampliE.getAmplifier_state()  "  + ampliE.getAmplifier_state() ) ;
			
			res_amplifier = ampliA.re_start(res_amplifier) ;
			// System.out.println("ampliA.getAmplifier_state()  "  + ampliA.getAmplifier_state() ) ;
			
			res_amplifier = ampliB.re_start(res_amplifier) ;
			// System.out.println("ampliB.getAmplifier_state()  "  + ampliB.getAmplifier_state() ) ;
			
			res_amplifier = ampliC.re_start(res_amplifier) ;
			// System.out.println("ampliC.getAmplifier_state()  "  + ampliC.getAmplifier_state() ) ;
			
			res_amplifier = ampliD.re_start(res_amplifier) ;
			// System.out.println("ampliD.getAmplifier_state()  "  + ampliD.getAmplifier_state() ) ;
			
			res_amplifier = ampliE.re_start(res_amplifier) ;
			// System.out.println("ampliE.getAmplifier_state()  "  + ampliE.getAmplifier_state() ) ;
			
			loop = loop + 1 ;
			if ( loop > 100 )  break;
		}
		
		// resultat cet essai
		return (int) res_amplifier ;
		
	}
	
	int solver7_amplificateur_part1 () {
		
		// appel amplifier 0
		System.out.println("Appel  solver7_amplificateur   " ) ;

		input_number = 0 ;
		inputs[0]  = phase_settings [0] ; // a modifier
		inputs[1]  = 0 ; // fixe
		res_amplifier= solver7_amplifier () ; 
		// System.out.println(" amplifier 0  " + res_amplifier  ) ;

		// amplifier  1
		// System.out.println("Appel  amplifier 1  " ) ;
		input_number = 0 ;
		inputs[0]  = phase_settings [1] ; // a modifier phase_settings
		inputs[1]  = (int)res_amplifier ;
		res_amplifier= solver7_amplifier () ; 
		// System.out.println(" amplifier 1  " + res_amplifier  ) ;

		// amplifier  2
		// System.out.println("Appel  amplifier 2  " ) ;
		input_number = 0 ;
		inputs[0]  = phase_settings [2] ; // a modifier phase_settings
		inputs[1]  = (int)res_amplifier ;
		res_amplifier= solver7_amplifier () ; 
		// System.out.println(" amplifier 2  " + res_amplifier  ) ;


		// amplifier  3
		// System.out.println("Appel  amplifier 3  " ) ;
		input_number = 0 ;
		inputs[0]  = phase_settings [3] ; // a modifier phase_settings
		inputs[1]  = (int)res_amplifier ;
		res_amplifier= solver7_amplifier () ; 
		// System.out.println(" amplifier 3  " + res_amplifier  ) ;


		// amplifier  4
		// System.out.println("Appel  amplifier 4  " ) ;
		input_number = 0 ;
		inputs[0]  = phase_settings [4] ; // a modifier phase_settings
		inputs[1]  = (int)res_amplifier ;
		res_amplifier= solver7_amplifier () ; 
		// System.out.println(" amplifier 4  " + res_amplifier  ) ;

		//Integer.parseInt(res_amplifier) ;
 		return  (int)res_amplifier  ; 


	}

	void show_intru () {
		for ( int i = 0 ; i<nb_instruction ; i++) {
			System.out.println(" intruction "  + i +  "  "  + instruction.get(i) ) ;
		}
	}



	int solver7_amplifier () {
		// lecture fichier
		// System.out.println("Advent2019 Day 7 " );
		
		final int POSITION_MODE = 0  ;
		int mode_of_1 ;
		int mode_of_2 ;
		int op_code_2 ;


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

		// part 2 
		// inputs produces the output 19690720

		try {
			int pointeur = 0 ;			// pointeur programme
			// parseline
			//System.out.println("parseline line  = " + line ) ;
			String[] parts =line.split(",");
			// parse instruction

			while ( pointeur < parts.length) {

				operation =  Integer.parseInt(parts[pointeur]) ;

				// opcode complet
				String opcode = parts[pointeur];
				String opcode_complet;
				opcode_complet = StringUtils.leftPad(opcode, 5 , "0") ;
				// System.out.println(" opcode_complet =   " + opcode_complet  ) ;
				if ( operation == 0   ) { Runtime.getRuntime().halt (0) ; }

				mode_of_1 = Integer.parseInt(StringUtils.substring(opcode_complet, 2, 3) ) ;
				mode_of_2 = Integer.parseInt(StringUtils.substring(opcode_complet, 1, 2 ) ) ;
				//mode_of_3 = Integer.parseInt(StringUtils.substring(opcode_complet, 0, 1) ) ;
				op_code_2    = Integer.parseInt(StringUtils.substring(opcode_complet, 3, 5) )    ;


				// le micro code
				switch (op_code_2 ) {
				case 1 :
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						// System.out.println(" pos_operande1 =   " + pos_operande1  ) ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						// System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						// System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						// System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;
					}

					// System.out.println(" addition  "  + parts[pos_operande1 ] + " " + parts[pos_operande2 ] ) ; 
					resutl = ( operande1   + operande2   )   ;
					pos_result = Integer.parseInt(parts[pointeur + 3 ]) ;
					parts[pos_result] = String.valueOf(resutl) ;
					pointeur = pointeur + 4 ;
					//
					//					Integer.parseInt(parts[pos_operande1])  == Integer.parseInt(parts[pos_operande2]) ) {
					//						pointeur = Integer.parseInt(parts[pointeur + 3])  ;

					// System.out.println(" result "  + resutl ) ; 

					break ;
				case 2 :
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						// System.out.println(" pos_operande1 =   " + pos_operande1  ) ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						// System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						//System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						// System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						//System.out.println(" operande2     =   " + operande2  ) ;
					}
					//System.out.println(" addition  "  + parts[pos_operande1 ] + " " + parts[pos_operande2 ] ) ; 
					resutl = ( operande1   * operande2   )   ;
					// System.out.println(" result "  + resutl ) ;
					pos_result = Integer.parseInt(parts[pointeur + 3 ]) ;
					parts[pos_result] = String.valueOf(resutl) ;
					pointeur = pointeur + 4 ;

					break ;
				case 3 :
					//System.out.println(" opcode =   " + operation  + " input value ") ;
					pointeur = pointeur  + 1 ;
					pos_result    =  Integer.parseInt(parts[pointeur]) ;
					parts[pos_result] = String.valueOf(inputs[input_number] );	// utilisation input  input_number
					input_number = input_number + 1 ;							// la suivante

					//parts[pos_result] = String.valueOf(my_input) ;
					pointeur = pointeur  + 1 ;
					//System.out.println(" opcode 3 pos_result  =   " + pos_result  + "  valeur  " + parts[pos_result] ) ;
					break ;
				case 4 :
					//System.out.println(" opcode =   " + operation   + " ouput ") ;
					if (mode_of_1 == POSITION_MODE) {
						pos_result = Integer.parseInt(parts[pointeur +1  ]) ;
						 //System.out.println(" pos_result =   " + pos_result + " 4  => output : "  ) ;

					} else {
						pos_result    =  pointeur +1  ;
						 System.out.println(" pos_result     =   " + pos_result  ) ;
						
					}


					resutl = Integer.parseInt(parts[pos_result] );
					System.out.println(" pos_result =   " + pos_result + "  => output : "  + parts[pos_result] ) ;
					resultat = resutl ;

					pointeur = pointeur  + 2 ;

					break ;
				case 5 :
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						// System.out.println(" pos_operande1 =   " + pos_operande1 + " jump-if-true: "  ) ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						// System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						// System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						// System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;
					}
					// System.out.println(" opcode =   " + operation  ) ;
					// System.out.println(" jump if true =   " + operation  ) ;

					if (     operande1  != 0 ) {
						pointeur = operande2   ;
					} else {
						pointeur = pointeur  + 3 ;
					}
					// System.out.println(" new pointeur =  "  + pointeur ) ;
					break ;
				case 6 :
					//  jump-if-false:
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						// System.out.println(" pos_operande1 =   " + pos_operande1   + " jump-if-false: ") ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						// System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						// System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						// System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;
					}
					// System.out.println(" opcode =   " + operation  ) ;
					// System.out.println(" jump if false =   " + operation  ) ;
					if (      operande1  == 0 ) {
						pointeur = operande2   ;
					} else {
						pointeur = pointeur  + 3 ;
					}
					// System.out.println(" new pointeur =  "  + pointeur ) ;
					break ;
				case 7 :
					// less than 
					// recherche des operandes
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						// System.out.println(" pos_operande1 =   " + pos_operande1 + " less than " ) ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						// System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						// System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						// System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;
					}
					pos_result =  Integer.parseInt(parts[pointeur + 3 ]) ;


					// System.out.println(" opcode =   " + operation  ) ;
					// System.out.println("  less =   " + operation  ) ;
					// pointeur = pointeur  + 1 ;


					if (      operande1  < operande2 ) {
						parts[pos_result] = String.valueOf(1) ;
						// System.out.println("  less =  pos_result = " + pos_result + " 1"  ) ;
					} else {
						parts[pos_result] = String.valueOf(0) ;
						// System.out.println("  less =  pos_result = " + pos_result + " 0"  ) ;
					}
					pointeur = pointeur + 4 ;
					// System.out.println(" new pointeur =  "  + pointeur ) ;
					break ;
				case 8 :
					// recherche des operandes de equals
					if (mode_of_1 == POSITION_MODE) {
						pos_operande1 = Integer.parseInt(parts[pointeur +1  ]) ;
						//System.out.println(" pos_operande1 =   " + pos_operande1  +  " equals" ) ;
						operande1 = Integer.parseInt(parts[pos_operande1] ) ;
						// System.out.println(" operande1     =   " + operande1  ) ;
					} else {
						operande1 = Integer.parseInt(parts[pointeur + 1 ] ) ;
						// System.out.println(" operande     =   " + operande1  ) ;
					}
					// deuxieme terme
					if (mode_of_2 == POSITION_MODE) {
						pos_operande2 = Integer.parseInt(parts[pointeur + 2 ]) ;
						// System.out.println(" pos_operande2 =   " + pos_operande2  ) ;
						operande2 = Integer.parseInt(parts[pos_operande2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;

					} else {
						operande2 = Integer.parseInt(parts[pointeur + 2] ) ;
						// System.out.println(" operande2     =   " + operande2  ) ;
					}

					pos_result =  Integer.parseInt(parts[pointeur + 3 ]) ;

					// System.out.println(" opcode =   " + operation  ) ;
					// System.out.println(" equals =   " + operation  ) ;
					// pointeur = pointeur  + 1 ;
					if (      operande1  == operande2 ) {
						parts[pos_result] = String.valueOf(1) ;
					} else {
						parts[pos_result] = String.valueOf(0) ;
					}
					pointeur = pointeur + 4 ;				
					// System.out.println(" new pointeur =  "  + pointeur ) ;

					break ;
				case 99 :
					//System.out.println(" opcode =   " + operation  ) ;
					// System.out.println(" fin  "  ) ;
					// System.out.println(" resultat =   " + resultat  ) ;
					// do not halt this process but point out
					pointeur  = parts.length + 1 ;   // provoque la sortie
					// Runtime.getRuntime().halt(0);
					break ;
				} 	//end switch (op_code_2 )

			}		// end while
			return  resultat  ;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + line, e);
			//e.printStackTrace();
		}


	}
	void read_line( String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			/* lecture ligne  */
			line = reader.readLine() ; 
			// fermeture 
			reader.close();
			System.out.println(" read done "   ) ;
			line = line.trim() ;
			// parseline
			//System.out.println("parseline line  = " + line ) ;

			String[] parts_ori =line.split(",");
			for ( int i=0; i<parts_ori.length ; i++) {
				instruction.addElement(parts_ori[i]);
			}
			// nomber  instruction
			nb_instruction = parts_ori.length ; 
			// show
			// show_intru () ;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + filename , e);
			//e.printStackTrace();
		}

	}

}


