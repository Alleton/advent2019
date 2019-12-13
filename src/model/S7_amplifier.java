package model;


import org.apache.commons.lang3.StringUtils;

public class S7_amplifier {

	int amplifier_number ; // numero
	/**
	 * @return the amplifier_state
	 */
	public int getAmplifier_state() {
		return amplifier_state;
	}


	/**
	 * @param amplifier_state the amplifier_state to set
	 */
	public void setAmplifier_state(int amplifier_state) {
		this.amplifier_state = amplifier_state;
	}
	int amplifier_output ; // numero de loutput ( 99 si THRUSTERS )


	final  int THRUSTERS = 99 ; 
	int amplifier_state  = 0  ; // tant que pas atteint THRUSTERS
	
	String line = null;
	int res_amplifier ; 
	int phase_setting ;		// permier input
	int input ;		// deuxieme input
	int inputs[]  = new int[2]; // combining both statements in one
	
	int pointeur ;
	int operation ; 
	int input_number = 0 ;   	// pour lire les 2 inputs
	
	
	final int POSITION_MODE = 0  ;
	
	
	
	// creation avec son numero ,  la ligne de codes 
	public S7_amplifier ( int  nom  ,int sortie ,  String i_line , int i_phase_setting  , int i_input  ){
		this.amplifier_number = nom ;
		this.amplifier_output = sortie ;
		this.line = i_line ;
		this.phase_setting = i_phase_setting ;
		this.input    = i_input;
		pointeur = 0 ; 
		
	}

	
	public int   start (  ) {
		System.out.println(" start =  ampli " + this.amplifier_number  ) ;
		
		int mode_of_1 ;
		int mode_of_2 ;
		int op_code_2 ;
		
		inputs[0] = this.phase_setting ;
		inputs[1] = this.input ;
		


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

		
		
		String[] parts =line.split(",");
		while ( amplifier_state == 0 ) { 
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
				System.out.println(" opcode =   " + operation  + " input value ") ;
				if (input_number <= 1  ) {
					pointeur = pointeur  + 1 ;
					pos_result    =  Integer.parseInt(parts[pointeur]) ;
					parts[pos_result] = String.valueOf(inputs[input_number] );	// utilisation input  input_number
					input_number = input_number + 1 ;							// la suivante

					//parts[pos_result] = String.valueOf(my_input) ;
					pointeur = pointeur  + 1 ;
					System.out.println(" opcode 3 input  =   " + pos_result  + "  valeur  " + parts[pos_result] ) ;
	
				} else {
					input_number = input_number - 1 ;							// on va attendre 
					amplifier_state = operation;
				}
				
				break ;
			case 4 :
				//System.out.println(" opcode =   " + operation   + " ouput ") ;
				if (mode_of_1 == POSITION_MODE) {
					pos_result = Integer.parseInt(parts[pointeur +1  ]) ;
					 //System.out.println(" pos_result =   " + pos_result + " 4  => output : "  ) ;

				} else {
					pos_result    =  pointeur +1  ;
					 //System.out.println(" pos_result     =   " + pos_result  ) ;
					
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
				// pointeur  = parts.length + 1 ;   // provoque la sortie
				// Runtime.getRuntime().halt(0);
				amplifier_state = operation;           // provoque la sortie
				break ;
			} 	//end switch (op_code_2 )

		}		// end while
		return resultat ;
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @return the amplifier_number
	 */
	public int getAmplifier_number() {
		return amplifier_number;
	}
	/**
	 * @param amplifier_number the amplifier_number to set
	 */
	public void setAmplifier_number(int amplifier_number) {
		this.amplifier_number = amplifier_number;
	}
	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}
	/**
	 * @param line the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}
	/**
	 * @return the res_amplifier
	 */
	public int getRes_amplifier() {
		return res_amplifier;
	}
	/**
	 * @param res_amplifier the res_amplifier to set
	 */
	public void setRes_amplifier(int res_amplifier) {
		this.res_amplifier = res_amplifier;
	}
	/**
	 * @return the phase_setting
	 */
	public int getPhase_setting() {
		return phase_setting;
	}
	/**
	 * @param phase_setting the phase_setting to set
	 */
	public void setPhase_setting(int phase_setting) {
		this.phase_setting = phase_setting;
	}
	/**
	 * @param input the input to set
	 */
	public void setInput(int input) {
		this.input = input;
	}
	/**
	 * @param inputs the inputs to set
	 */
	public void setInputs(int[] inputs) {
		this.inputs = inputs;
	}

	/**
	 * @return the amplifier_output
	 */
	public int getAmplifier_output() {
		return amplifier_output;
	}
	/**
	 * @param amplifier_output the amplifier_output to set
	 */
	public void setAmplifier_output(int amplifier_output) {
		this.amplifier_output = amplifier_output;
	}



	
	
	
	

	
} // end class S7_amplifier
