package model;

public class S9_amplifier {

	int amplifier_number ; // numero
	
	int mode_of_1 ;
	int mode_of_2 ;
	int mode_of_3 ;
	
	
	int op_code_2 ;
	int operation ;
	
	int pos_operande1 = 0 ;
	int pos_operande2 = 0 ;
	int pos_result = 0 ;
	int relative_base = 0 ;
	

	long operande1 = 0 ;
	long operande2 = 0 ;
	long operande3 = 0 ;
	long resutl    = 0 ;
	long resultat    = 0 ;

	int amplifier_output ; // numero de loutput ( 99 si THRUSTERS )


	final  long THRUSTERS = 99 ; 
	
	final int ADD	= 1 ;
	final int MUL   = 2 ;
	final int INPUT = 3 ;
	final int OUTPUT = 4 ;
	final int IF_TRUE = 5 ;
	final int IF_FALSE = 6 ;
	final int LESS     = 7 ;
	final int EQUALS   = 8 ;
	final int RELATIV  = 9 ;
	
	
	
	int amplifier_state  = 0  ; // tant que pas atteint THRUSTERS
	
	String line = null;
	long res_amplifier ; 
	long phase_setting ;		// permier input
	int input ;		// deuxieme input
	long inputs[]  = new long [2]; // combining both statements in one
	
	long pointeur ;
	int input_number = 0 ;   	// pour lire les 2 inputs
	
	
	final int POSITION_MODE  = 0  ;
	final int IMMEDIATE_MODE = 1  ;
	final int RELATIVE_MODE  = 2  ;
	
	int nb_instructions = 0 ;
	
	long[] parts = new long [3001];
	
	
	
	
	// creation avec son numero ,  la ligne de codes 
	public S9_amplifier ( int  nom  ,int sortie ,  String i_line , long i_phase_setting  , int i_input  ){
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
			parts[i] =  Long.parseLong(parts_string[i])  ;
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
		
		for ( int i = 0 ; i <2 ; i++ ){
			System.out.println("inputs [ " + i + " = " + inputs[ i ]  ) ;
		}
		
		start_result = run()  ;
		System.out.println("start =  ampli " + this.amplifier_number   + " result = " + start_result ) ;
		return start_result ; 
	}

	// relance
	public long re_start (int i_input) {
		System.out.println(" re start =  ampli " + this.amplifier_number  ) ;
		
		this.amplifier_state = 0 ; 		// plus en attente 
		this.inputs[1] = i_input;
		//String[] parts =line.split(",");
		return run()  ;
	}

	
	public long   run (  ) {
		 System.out.println(" run =  ampli " + this.amplifier_number  ) ;


		while ( amplifier_state == 0 ) { 
			operation =  (int) parts[(int)pointeur];   ;

			// opcode complet
			int opcode = (int)parts[(int)pointeur];
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
			
			//private int lecture_oper1 ( int num_oper , int mode) {
			pos_operande1 = lecture_pos_oper (1,mode_of_1 ) ;
			pos_operande2 = lecture_pos_oper (2,mode_of_2 ) ;
			pos_result    = lecture_pos_oper (3,mode_of_3 ) ; ;
			
			operande1 = parts[pos_operande1];
			operande2 = parts[pos_operande2];

			
			///////////////////
			
			// le micro code
			switch (op_code_2 ) {
			case ADD :
				resutl = (long)( (long)operande1   + (long)operande2   )   ;
				parts[pos_result] = resutl ;
				pointeur = pointeur + 4 ;
				System.out.println("ADD  result "  + resutl ) ; 

				break ;
			case MUL :
				//System.out.println(" multiplication  "  + parts[pos_operande1 ] + " " + parts[pos_operande2 ] ) ; 
				resutl = (long)( (long)operande1   * (long)operande2   )   ;
				System.out.println("MUL result "  + resutl ) ;
				parts[pos_result] = resutl ;
				pointeur = pointeur + 4 ;

				break ;
			case INPUT :
				 System.out.println(" opcode =  input " + operation  + " input value ") ;
				if (input_number <= 1  ) {
					pointeur = pointeur  + 1 ;
					System.out.println(" opcode 3 input  =   " + inputs[input_number]  ) ;
					System.out.println(" opcode 3 pos_operande1  =   " + pos_operande1   ) ;
					
					//System.out.println(" opcode 3 input  =   " + pos_result  + "  valeur  " + parts[pos_result] ) ;
					//System.out.println(" opcode 3 input  =   " + pos_result  + "  valeur  " + parts[pos_result] ) ;
					
					parts[pos_operande1] = (long) inputs[input_number] ;	// utilisation input  input_number
					input_number = input_number + 1 ;							// la suivante

					//parts[pos_result] = String.valueOf(my_input) ;
					pointeur = pointeur  + 1 ;
					System.out.println(" opcode 3 input  =   " + pos_operande1  + "  valeur  " + parts[pos_operande1] ) ;
	
				} else {
					input_number = input_number - 1 ;							// on va attendre 
					amplifier_state = (int)operation;
				}
				System.out.println(" opcode 3 input resultat en  =   " + pos_operande1  + "  valeur  " + parts[pos_operande1] ) ;
				
				break ;
			case OUTPUT :
				//System.out.println(" opcode =   " + operation   + " ouput ") ;
				resutl = parts[pos_operande1] ;
				System.out.println("  => output de  : "   + pos_operande1 +  " soit " + resutl ) ;
				resultat = resutl ;

				pointeur = pointeur  + 2 ;

				break ;
			case IF_TRUE :
				// recherche des operandes
				if (     operande1  != 0 ) {
					pointeur = operande2   ;
				} else {
					pointeur = pointeur  + 3 ;
				}
				 System.out.println("IF_TRUE new pointeur =  "  + pointeur ) ;
				break ;
			case IF_FALSE :
				//  jump-if-false:
				// recherche des operandes
				if (      operande1  == 0 ) {
					pointeur = operande2   ;
				} else {
					pointeur = pointeur  + 3 ;
				}
				System.out.println("IF_FALSE new pointeur =  "  + pointeur ) ;
				break ;
			case LESS :
				// less than 
				
				if (      operande1  < operande2 ) {
					parts[pos_result] = 1 ;
					System.out.println("LESS  pos_result = " + pos_result + " 1"  ) ;
				} else {
					parts[pos_result] = 0 ;
					// System.out.println("  less =  pos_result = " + pos_result + " 0"  ) ;
				}
				pointeur = pointeur + 4 ;
				System.out.println("LESS new pointeur =  "  + pointeur ) ;
				break ;
				
			case EQUALS :

				// System.out.println(" opcode =   " + operation  ) ;
				// System.out.println(" equals =   " + operation  ) ;
				// pointeur = pointeur  + 1 ;
				if (      operande1  == operande2 ) {
					parts[pos_result] = 1 ;
				} else {
					parts[pos_result] = 0 ;
				}
				pointeur = pointeur + 4 ;				
				System.out.println("EQUALS new pointeur =  "  + pointeur ) ;

				break ;

			case RELATIV :
				relative_base = relative_base + (int)operande1 ;
				pointeur = pointeur + 2 ;
				System.out.println("RELATIV new relativ =  "  + relative_base ) ;
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
		System.out.println("**  resultat =   " + resultat  ) ;
		
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
	 * @return the res_amplifier
	 */
	public long getRes_amplifier() {
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
	public long getPhase_setting() {
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
	public void setInputs(long[] inputs) {
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



	
	/**
	 * @return the amplifier_state
	 */
	public int getAmplifier_state() {
		return amplifier_state;
	}

	/**
	 * @return the amplifier_state
	 */
	public int getAmplifier_pointeur() {
		return (int)this.pointeur;
	}
	/**
	 * @return the amplifier_state
	 */
//	public int getAmplifier_state() {
//		return amplifier_state;
//	}
	

	/**
	 * @param amplifier_state the amplifier_state to set
	 */
	public void setAmplifier_state(int amplifier_state) {
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
	/*
	 * 	final int POSITION_MODE  = 0  ;
	final int IMMEDIATE_MODE = 1  ;
	final int RELATIVE_MODE  = 2  ;
	 */
	
	private int lecture_pos_oper ( int num_oper , int mode) {
		int pos_oper=0;
		switch ( mode ) {
		case POSITION_MODE :
			pos_oper = (int) parts[(int) (pointeur + num_oper)  ] ;
			// System.out.println(" pos_operande1 =   " + pos_operande1  ) ;
			if (pos_oper >= parts.length  ) {
				System.out.println(" pos_oper =   " + pos_oper  ) ;
				pos_oper = 0 ;
			}
			
			break ;
		case IMMEDIATE_MODE :
			pos_oper = (int) (pointeur + num_oper  ) ;
			if (pos_oper >= parts.length  ) {
				System.out.println(" pos_oper =   " + pos_oper  ) ;
				pos_oper = 0 ;
			}
			break ;
		case RELATIVE_MODE :
			// relative_base
			pos_oper = (int) (parts[(int)pointeur  +num_oper ] )+ relative_base ;
			if (pos_oper >= parts.length  ) {
				System.out.println(" pos_oper =   " + pos_oper  ) ;
				pos_oper = 0 ;
			}break ;
			
		}
		
		return pos_oper;
	}
	

	

	
} // end class S7_amplifier
