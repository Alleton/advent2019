package model;

public class S7_amplifier {

	long amplifier_number ; // numero
	
	long mode_of_1 ;
	long mode_of_2 ;
	long mode_of_3 ;
	
	
	long op_code_2 ;
	long operation ;
	
	long pos_operande1 = 0 ;
	long pos_operande2 = 0 ;
	long pos_result = 0 ;
	long relative_base = 0 ;
	

	long operande1 = 0 ;
	long operande2 = 0 ;
	long operande3 = 0 ;
	long resutl    = 0 ;
	long resultat    = 0 ;

	long amplifier_output ; // numero de loutput ( 99 si THRUSTERS )


	final  long THRUSTERS = 99 ; 
	long amplifier_state  = 0  ; // tant que pas attelong THRUSTERS
	
	String line = null;
	long res_amplifier ; 
	long phase_setting ;		// permier input
	long input ;		// deuxieme input
	long inputs[]  = new long[2]; // combining both statements in one
	
	long pointeur ;
	long input_number = 0 ;   	// pour lire les 2 inputs
	
	
	final long POSITION_MODE = 0  ;
	
	long nb_instructions = 0 ;
	
	long[] parts = new long [3001];
	
	
	
	
	// creation avec son numero ,  la ligne de codes 
	public S7_amplifier ( long  nom  ,long sortie ,  String i_line , long i_phase_setting  , long i_input  ){
		this.amplifier_number = nom ;
		this.amplifier_output = sortie ;
		this.line = i_line ;
		this.phase_setting = i_phase_setting ;
		this.input    = i_input;
		pointeur = 0 ; 
		//parts[] = = i_line.split(",");
		//parts  = line.split(",");
		//String [] parts_string = new String [1000];
		String []  parts_string  = line.split(",");
		nb_instructions = parts_string.length;  // la taille !!
		
		for ( int i = 0 ; i < parts_string.length ; i ++  ) {
			parts[i] =  Integer.parseInt(parts_string[i])  ;
		}
		for ( int i = parts_string.length +1 ; i < parts.length -1 ; i ++  ) {
			parts[i] =  0 ;
		}
		/******************
		 * 
		 */
		
	
	}

	public long start () {
		long start_result ;
		// System.out.println(" start =  ampli " + this.amplifier_number  )
		;
		this.inputs[0] = this.phase_setting ;
		this.inputs[1] = this.input ;
		//String[] parts =line.split(",");
		start_result = run()  ;
		System.out.println("start =  ampli " + this.amplifier_number   + " result = " + start_result ) ;
		return start_result ; 
	}

	// relance
	public long re_start (long i_input) {
		System.out.println(" re start =  ampli " + this.amplifier_number  ) ;
		
		this.amplifier_state = 0 ; 		// plus en attente 
		this.inputs[1] = i_input;
		//String[] parts =line.split(",");
		return run()  ;
	}

	
	public long   run (  ) {
		 System.out.println(" run =  ampli " + this.amplifier_number  ) ;


		while ( amplifier_state == 0 ) { 
			operation =   parts[(int)pointeur] ;

			// opcode complet
			long opcode = parts[(int)pointeur];
			//String opcode_complet;
			// opcode_complet = StringUtils.leftPad(opcode, 5 , "0") ;
			//System.out.println(" pointeur =   " + pointeur  ) ;
			//System.out.println(" opcode_complet =   " + opcode  ) ;
			if ( operation == 0   ) { Runtime.getRuntime().halt (0) ; }


			// op_code_2    = Integer.parseInt(StringUtils.substring(opcode_complet, 3, 5) )    ;
			op_code_2 =   opcode % 100 ;
			mode_of_1 =   ( (opcode  / 100 ) % 10 ) ;  
			mode_of_2 =   ( (opcode  / 1000 ) % 10 ) ;
			mode_of_3 =   opcode  / 10000  ;
			
			
			
	// lectutr position operande 		
			
			//private long lecture_oper1 ( long num_oper , long mode) {
			pos_operande1 = lecture_pos_oper (1,mode_of_1 ) ;
			pos_operande2 = lecture_pos_oper (2,mode_of_2 ) ;
			pos_result    = parts[(int) (pointeur +3  )] ;
			
			operande1 = parts[(int)pos_operande1];
			operande2 = parts[(int)pos_operande2];

			
			///////////////////
			
			// le micro code
			switch ((int)op_code_2 ) {
			case 1 :
				resutl = ( operande1   + operande2   )   ;
				parts[(int)pos_result] = resutl ;
				pointeur = pointeur + 4 ;
				// System.out.println(" result "  + resutl ) ; 

				break ;
			case 2 :
				//System.out.println(" multiplication  "  + parts[pos_operande1 ] + " " + parts[pos_operande2 ] ) ; 
				resutl = ( operande1   * operande2   )   ;
				// System.out.println("multply result "  + resutl ) ;
				parts[(int)pos_result] = resutl ;
				pointeur = pointeur + 4 ;

				break ;
			case 3 :
				// System.out.println(" opcode =  input " + operation  + " input value ") ;
				if (input_number <= 1  ) {
					pointeur = pointeur  + 1 ;
					
					parts[(int)pos_operande1] = inputs[(int)input_number] ;	// utilisation input  input_number
					input_number = input_number + 1 ;							// la suivante

					//parts[pos_result] = String.valueOf(my_input) ;
					pointeur = pointeur  + 1 ;
					// System.out.println(" opcode 3 input  =   " + pos_result  + "  valeur  " + parts[pos_result] ) ;
	
				} else {
					input_number = input_number - 1 ;							// on va attendre 
					amplifier_state = operation;
				}
				
				break ;
			case 4 :
				//System.out.println(" opcode =   " + operation   + " ouput ") ;
				resutl = parts[(int)pos_operande1] ;
				// System.out.println(" pos_result =   " + pos_result + "  => output : "  + parts[pos_result] ) ;
				resultat = resutl ;

				pointeur = pointeur  + 2 ;

				break ;
			case 5 :
				// recherche des operandes
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
				if (      operande1  == 0 ) {
					pointeur = operande2   ;
				} else {
					pointeur = pointeur  + 3 ;
				}
				// System.out.println(" new pointeur =  "  + pointeur ) ;
				break ;
			case 7 :
				// less than 
				
				if (      operande1  < operande2 ) {
					parts[(int)pos_result] = 1 ;
					// System.out.println("  less =  pos_result = " + pos_result + " 1"  ) ;
				} else {
					parts[(int)pos_result] = 0 ;
					// System.out.println("  less =  pos_result = " + pos_result + " 0"  ) ;
				}
				pointeur = pointeur + 4 ;
				// System.out.println(" new pointeur =  "  + pointeur ) ;
				break ;
				
			case 8 :

				// System.out.println(" opcode =   " + operation  ) ;
				// System.out.println(" equals =   " + operation  ) ;
				// pointeur = pointeur  + 1 ;
				if (      operande1  == operande2 ) {
					parts[(int)pos_result] = 1 ;
				} else {
					parts[(int)pos_result] = 0 ;
				}
				pointeur = pointeur + 4 ;				
				// System.out.println(" new pointeur =  "  + pointeur ) ;

				break ;

			case 9 :
				relative_base = relative_base + operande1 ;
				pointeur = pointeur + 2 ;
				break ;
			case 99 :
				//System.out.println(" opcode =   " + operation  ) ;
				// System.out.println(" fin  "  ) ;
				// System.out.println(" resultat =   " + resultat  ) ;
				// do not halt this process but polong out
				// pointeur  = parts.length + 1 ;   // provoque la sortie
				// Runtime.getRuntime().halt(0);
				amplifier_state = operation;           // provoque la sortie
				break ;
			} 	//end switch (op_code_2 )

		}		// end while
		System.out.println("**  resultat =   " + resultat  ) ;
		
		return resultat ;
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @return the amplifier_number
	 */
	public long getAmplifier_number() {
		return amplifier_number;
	}
	/**
	 * @param amplifier_number the amplifier_number to set
	 */
	public void setAmplifier_number(long amplifier_number) {
		this.amplifier_number = amplifier_number;
	}

	/**
	 * @return the res_amplifier
	 */
	public long getRes_amplifier() {
		return res_amplifier;
	}
	/**
	 * @param res_amplifier the res_amplifier to set
	 */
	public void setRes_amplifier(long res_amplifier) {
		this.res_amplifier = res_amplifier;
	}
	/**
	 * @return the phase_setting
	 */
	public long getPhase_setting() {
		return phase_setting;
	}
	/**
	 * @param phase_setting the phase_setting to set
	 */
	public void setPhase_setting(long phase_setting) {
		this.phase_setting = phase_setting;
	}
	/**
	 * @param input the input to set
	 */
	public void setInput(long input) {
		this.input = input;
	}

	/**
	 * @return the amplifier_output
	 */
	public long getAmplifier_output() {
		return amplifier_output;
	}
	/**
	 * @param amplifier_output the amplifier_output to set
	 */
	public void setAmplifier_output(long amplifier_output) {
		this.amplifier_output = amplifier_output;
	}



	
	/**
	 * @return the amplifier_state
	 */
	public long getAmplifier_state() {
		return amplifier_state;
	}

	/**
	 * @return the amplifier_state
	 */
	public long getAmplifier_pointeur() {
		return this.pointeur;
	}
	/**
	 * @return the amplifier_state
	 */
//	public long getAmplifier_state() {
//		return amplifier_state;
//	}
	

	/**
	 * @param amplifier_state the amplifier_state to set
	 */
	public void setAmplifier_state(long amplifier_state) {
		this.amplifier_state = amplifier_state;
	}
	
//	switch ( mode_of_1 ) {
//	case 0 :
//		pos_operande1 = parts[pointeur +1  ] ;
//		// System.out.println(" pos_operande1 =   " + pos_operande1  ) ;
//		operande1 = parts[pos_operande1 ] ;
//		break ;
//	case 1 :
//		operande1 = parts[pointeur + 1  ] ;
//		break ;
//	case 2 :
//		break ;
	private long lecture_pos_oper ( long num_oper , long mode) {
		long pos_oper=0;
		switch ((int) mode ) {
		case 0 :
			pos_oper = parts[(int) (pointeur + num_oper)  ] ;
			// System.out.println(" pos_operande1 =   " + pos_operande1  ) ;
			if (pos_oper >= nb_instructions  ) {
				pos_oper = 0 ;
			}
			
			break ;
		case 1 :
			pos_oper = pointeur + num_oper   ;
			if (pos_oper >= nb_instructions  ) {
				pos_oper = 0 ;
			}
			break ;
		case 2 :
			break ;
			
		}
		
		return pos_oper;
	}
	

	

	
} // end class S7_amplifier
