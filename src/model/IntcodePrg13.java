package model;

import java.util.HashMap;

public class IntcodePrg13  {

	int amplifier_number ; // numero
	
	int mode_of_1 ;
	int mode_of_2 ;
	int mode_of_3 ;
	
	
	int op_code_2 ;
	// int operation ;
	
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
	final int EXIT  = 99 ;
	
	
	int amplifier_state  = 0  ; // tant que pas atteint THRUSTERS
	
	String line = null;
	long res_amplifier ; 
	//long phase_setting ;		// permier input
	int input ;					// deuxieme input
	// long inputs[]  = new long [2]; // combining both statements in one
	
	long pointeur    = 0L ;
	int input_number = 0 ;   	// pour lire les 2 inputs
	
	
	final int POSITION_MODE  = 0  ;
	final int IMMEDIATE_MODE = 1  ;
	final int RELATIVE_MODE  = 2  ;
	
	int nb_instructions = 0 ;
	
	long[] parts = new long [23001];
	
	HashMap <Integer, Long > les_instructions = new  HashMap <Integer  ,Long > ();
	
	
	// creation avec son numero ,  la ligne de codes 
	public IntcodePrg13  ( int  nom  ,int sortie ,  String i_line , long quarters  , int i_input  ){
		
		System.out.println(" creation =  ampli "  + nom) ;
		
		this.amplifier_number = nom ;
		this.amplifier_output = sortie ;
		this.line = i_line ;
		//this.phase_setting = i_phase_setting ;
		this.input    = i_input;
		pointeur = 0 ; 
		//parts[] = = i_line.split(",");
		//parts  = line.split(",");
		//String [] parts_string = new String [1000];
		String []  parts_string  = this.line.split(",");
		// nb_instructions = parts_string.length;  // la taille !!
		
		for ( int i = 0 ; i < parts_string.length ; i ++  ) {
			// parts[i] =  Long.parseLong(parts_string[i])  ;
			les_instructions.put(i, Long.parseLong( parts_string[i])) ;
		}
		
		System.out.println(" les_instructions " + les_instructions.size() ) ;
		for ( int i = 0 ; i < parts_string.length ; i ++  ) {
			// parts[i] =  Long.parseLong(parts_string[i])  ;
			// System.out.println(" instruction  " + i  + "  est " + les_instructions.get(i) ) ;
		}
	}
	
	public void add_quarter ( long quarter ) {
		/* modifi instruction 0 
		 * 
		 */
		System.out.println("before  quarters =   " + les_instructions.get(0)  ) ;
		les_instructions.put(0, quarter);
		System.out.println("after quarters =   " + les_instructions.get(0)  ) ;
	}

	public long start () {
		long start_result ;
		 System.out.println(" start =  ampli "  ) ;
		 System.out.println(" quarters =   " + les_instructions.get(0)  ) ;
		 
		
		// this.inputs[0] = this.phase_setting ;
		// this.inputs[1] = this.input ;
		//String[] parts =line.split(",");
		// this.input    = i_input; a l'init
		 System.out.println("start =  ampli " + this.amplifier_number   + " this.input = " + this.input ) ;
		start_result = run()  ;
		System.out.println("end start =  ampli " + this.amplifier_number   + " result = " + start_result ) ;
		return start_result ; 
	}

	// relance avec parametre ( sur un input qui vaut notre couleur )
	public long re_start (int i_input) {
		System.out.println(" re start avec  i_input " + i_input  ) ;
		
		this.amplifier_state = 0 ; 		// plus en attente 
		this.input = i_input;
		//String[] parts =line.split(",");
		return run()  ;
	}


	// relance sans parametre ( sur un output )
	public long re_start_output () {
		System.out.println(" re start sans param =  ampli " + this.amplifier_number  ) ;
		
		this.amplifier_state = 0 ; 		// plus en attente 
		
		//String[] parts =line.split(",");
		return run()  ;
	}

	
	public long   run (  ) {
		 // System.out.println(" run =  ampli " + this.amplifier_number  ) ;


		while ( amplifier_state == 0 ) { 
			//operation =  (int) parts[(int)pointeur];   ;
			// opcode complet
			//int opcode = (int)parts[(int)pointeur];
			//  les_instructions
			int opcode = Math.toIntExact ( les_instructions.get(  Math.toIntExact (pointeur)) ) ;

			// System.out.println(" opcode " + opcode  ) ;
			if ( opcode == 0   ) { Runtime.getRuntime().halt (0) ; }


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
			
			
			operande1 = get_operande( pos_operande1) ;
			operande2 = get_operande( pos_operande2) ;
			
			///////////////////
			
			// le micro code
			switch (op_code_2 ) {
			case ADD :
				resutl = operande1   + operande2      ;
				
				// parts[pos_result] = resutl ;
				les_instructions.put(pos_result, resutl ) ;
				
				pointeur = pointeur + 4 ;
				// System.out.println("ADD  result "  + resutl ) ; 
				amplifier_state = 0 ;        // running
				break ;

			case MUL :
				//System.out.println(" multiplication  "  + parts[pos_operande1 ] + " " + parts[pos_operande2 ] ) ; 
				resutl = operande1   * operande2      ;
				// System.out.println("MUL result "  + resutl ) ;
				// parts[pos_result] = resutl ;
				les_instructions.put(pos_result, resutl ) ;
				pointeur = pointeur + 4 ;
				amplifier_state = 0 ;        // running
				break ;

			case INPUT :
				System.out.println(" INPUT =  input " + input + " en "  + pos_operande1) ;
				// parts[pos_operande1] = (long) input ;	// utilisation input  input_number
				les_instructions.put(pos_operande1 , (long) input ) ;
				input_number = input_number + 1 ;							// la suivante
				pointeur = pointeur  + 2 ;
				// amplifier_state = INPUT;
				amplifier_state = 0 ;        // running
				break ;

			case OUTPUT :
				//System.out.println("OUTPUT opcode =   " + operation   + " ouput ") ;
				// resutl = parts[pos_operande1] ;
				resutl = les_instructions.get(pos_operande1) ;
				System.out.println(" pointeur = " + pointeur + " => output de  : "   + pos_operande1 +  " soit " + resutl ) ;
				resultat = resutl ;
				pointeur = pointeur  + 2 ;
				amplifier_state = OUTPUT;           // provoque la sortie  et demandera un restart
				break ;

			case IF_TRUE :
				// recherche des operandes
				if (     operande1  != 0 ) {
					pointeur = operande2   ;
				} else {
					pointeur = pointeur  + 3 ;
				}
				 //System.out.println("IF_TRUE new pointeur =  "  + pointeur ) ;
				 amplifier_state = 0 ;        // running
				break ;

			case IF_FALSE :
				//  jump-if-false:
				// recherche des operandes
				if (      operande1  == 0 ) {
					pointeur = operande2   ;
				} else {
					pointeur = pointeur  + 3 ;
				}
				// System.out.println("IF_FALSE new pointeur =  "  + pointeur ) ;
				amplifier_state = 0 ;        // running
				break ;

			case LESS :
				// less than 
				if (      operande1  < operande2 ) {
					// parts[pos_result] = 1 ;
					les_instructions.put(pos_result , 1L ) ;
					// System.out.println("LESS  pos_result = " + pos_result + " 1"  ) ;
				} else {
					//parts[pos_result] = 0 ;
					les_instructions.put(pos_result , 0L ) ;
					// System.out.println("  less =  pos_result = " + pos_result + " 0"  ) ;
				}
				pointeur = pointeur + 4 ;
				// System.out.println("LESS new pointeur =  "  + pointeur ) ;
				amplifier_state = 0 ;        // running
				break ;
				
			case EQUALS :
				if (      operande1  == operande2 ) {
					// parts[pos_result] = 1 ;
					les_instructions.put(pos_result , 1L ) ;
				} else {
					les_instructions.put(pos_result , 0L ) ;
					// parts[pos_result] = 0 ;
				}
				pointeur = pointeur + 4 ;				
				// System.out.println("EQUALS new pointeur =  "  + pointeur ) ;
				amplifier_state = 0 ;        // running
				break ;

			case RELATIV :
				relative_base = relative_base + (int)operande1 ;
				pointeur = pointeur + 2 ;
				amplifier_state = 0 ;        // running
				// System.out.println("RELATIV new relativ =  "  + relative_base ) ;
				break ;
			case EXIT :
				amplifier_state = op_code_2;           // provoque la sortie
				break ;
			} 	//end switch (op_code_2 )

		}		// end while
		System.out.println("end run **  resultat =   " + resultat  ) ;
		
		return resultat ;
	}	// end run
	
	
	/**
	 * 
	 * 
	 * 
	 * @return the amplifier_number
	 */
	public int getAmplifier_number() {
		return amplifier_number;
	}
	
	
	public long getInstruction ( int i) {
		return les_instructions.get(i) ;
		
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
	 * @param input the input to set
	 */
	public void setInput(int input) {
		this.input = input;
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
	
	
	private long get_operande( int adresse ) {
		//operande1 = les_instructions.get( pos_operande1) ;
		long operande = 0L ;
		if ( les_instructions.containsKey(adresse) ) {
			operande = les_instructions.get( adresse) ;
		}
		return operande ;
	}

	
	private int lecture_pos_oper ( int num_oper , int mode) {
		int pos_oper=0;
		long adresse = 0 ;
		switch ( mode ) {
		case POSITION_MODE :
			// pos_oper = (int) parts[(int) (pointeur + num_oper)  ] ;
			//a = les_instructions.put(pos_result , 1L ) ;
			 adresse = les_instructions.get((int) pointeur + (int)num_oper  ) ;
			 if ( adresse > Integer.MAX_VALUE ) {
				 pos_oper = 0 ; 
 			 } else {
 				pos_oper= Math.toIntExact (adresse ) ;
 			 }
			// pos_oper =  Math.toIntExact (les_instructions.get((int) pointeur + (int)num_oper  )  );
			
			
			// System.out.println(" pos_operande1 =   " + pos_operande1  ) ;
			if (pos_oper >= parts.length  ) {
				System.out.println(" pos_oper =   " + pos_oper  ) ;
				// pos_oper = 0 ;
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
			// pos_oper = (int) (parts[(int)pointeur  +num_oper ] )+ relative_base ;
			
			//pos_oper =  Math.toIntExact (les_instructions.get( (int)pointeur + (int)num_oper  )  ) + relative_base ;
			
				// pos_oper = 0 ;
			
			adresse = les_instructions.get((int) pointeur + (int)num_oper  ) + relative_base ;
				 if ( adresse > Integer.MAX_VALUE ) {
					 pos_oper = 0 ; 
	 			 } else {
	 				pos_oper= Math.toIntExact (adresse ) ;
	 			 }
					
			break ;
			
		}
		
		return pos_oper;
	}
	

	

	
} // end class IntcodePrg13 
