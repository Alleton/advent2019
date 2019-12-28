package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*; 

import model.IntcodeProgam;  // la classe  IntcodeProgam
import model.S10_asteroid;
import model.S11_surface ;


public class Solver11 {
	String line ;
	 // Vector  instruction =new Vector  ();
	int nb_instruction ;
	long  res_intcode ;   // resultat du long  intcode  computer;  

	long[] outputs = new long[2]; // pour stoker les 2 outputs
	int   outputs_numero = 0 ;
	final int INPUT = 3 ;
	final int OUTPUT = 4 ;
	final int EXIT  = 99 ;

	final int NORTH = 0 ;
	final int EAST  = 1 ;
	final int SOUTH = 2 ;
	final int WEST  = 3 ;

	final int WHITE = 1 ;
	final int BLACK = 0 ;

	// la position du robot
	int x_robot = 0 ;
	int y_robot = 0 ;
	int direction 		= NORTH ; 	// au depart
	int notre_couleur 	= BLACK ;	// au depart

	// les extremites de la surface a peindre
	int min_x = 0 ;
	int min_y = 0 ;
	int max_x = 0 ;
	int max_y = 0 ;
	
	// la map de la surface
	HashMap <Integer, S11_surface> hash_surface = new  HashMap <Integer ,S11_surface> ();


	String solver11 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019 day 11" );
		System.out.println("filename = " + sfname );
		read_line( sfname) ;
		outputs_numero = 0 ; // ben oui

		// position debut key = 0 , x = 0 y = 0 color = 0
		S11_surface debut = new S11_surface (x_robot , y_robot , WHITE) ;
		hash_surface.put(0,  debut) ;  


		IntcodeProgam ampliA = new IntcodeProgam ( 0 ,1 , line , 2, WHITE ) ;

		res_intcode = ampliA.start() ;

		System.out.println("ampliA state  = " + ampliA.getAmplifier_state() );
		while (ampliA.getAmplifier_state()!=  EXIT) {
			// le micro code
			switch (ampliA.getAmplifier_state() ) {
			case OUTPUT :{
				// OUTPUT
				// memorisation 
				// System.out.println("ampliA  OUTPUT state  = " + ampliA.getAmplifier_state() );
				outputs [outputs_numero ] =  res_intcode;
				outputs_numero = outputs_numero + 1 ; /// suivant

				if ( outputs_numero > 1 ) {
					//
					paint_move( ( int) outputs[0]  , ( int)  outputs[1] ); // colori et bouge
					int notre_couleur  = trouve_notre_couleur();
					// 

					outputs_numero = 0 ; // ben oui
					res_intcode= ampliA.re_start (notre_couleur) ;
					break ;
					
				} else {
					res_intcode= ampliA.re_start_output () ;     //
					break ;
				}
			}
			case INPUT : {
				// 
				// on fourni la couleur de notre position
				System.out.println("ampliA  INPUT state  = " + ampliA.getAmplifier_state() );

				break ; 
			}
			case EXIT :{
				// Runtime.getRuntime().halt (0) ;
				break ;

			}

			} // fin switch
		} 	// ampliA.getAmplifier_state()!=  EXIT) 

		System.out.println("hash_surface " + hash_surface.size());
		
		
		// calcule les min et max
		trouve_surface () ;
		// visualise le resultat
		
		visu_final () ;
		Runtime.getRuntime().halt (0) ;


		return sfname ;
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
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to load " + filename , e);
			//e.printStackTrace();
		}

	}

	int paint_move( int couleur  ,  int change_direction ) {
		// premiere valeur couleur a mettre
		// deuxieme valeur direction

		// recherche ( et creation si besoin ) 		S11_surface debut = new S11_surface (BLACK ,x_robot , y_robot ) ;
		// hash_surface
		System.out.println("paint_move " + x_robot  + "  " + y_robot  + "couleur " + couleur 
				+ " change_direction " + change_direction )  ;
		boolean trouve = false ;

		for ( int i = 0 ; i < hash_surface.size() ; i++  ) {

			if ( hash_surface.get(i).getX() == x_robot  && hash_surface.get(i).getY() == y_robot ) {
				// nous sommes sur une syrface connue
				hash_surface.get(i).setCouleur(couleur);
				trouve = true ;
				break;		// pas la peinr de continuer
			} // mm x et y  
		} // booucle for

		// si on a pas trouve il faut creer 
		// position debut key = 0 , x = 0 y = 0 color = 0
		// S11_surface debut = new S11_surface (BLACK ,x_robot , y_robot ) ;

		if ( ! trouve ) {
			System.out.println("pas trouve " + x_robot  + "  " + y_robot )  ;
			System.out.println("dans has surface avec taille "  + hash_surface.size() ) ;
			hash_surface.put(hash_surface.size() ,  new S11_surface (x_robot , y_robot , couleur)) ;
			System.out.println("nouvell  hash surface taille "  + hash_surface.size() ) ;
		}

		// maintenant quelle est notre direction

		switch (change_direction ) {
		case 1 : 
			direction = direction + 1 ;
			if ( direction  == 4 )  direction = NORTH ;
			break ;
		case 0 : 
			direction = direction - 1 ;
			if ( direction  < 0 )  direction = WEST ;
			break ;

		}

		switch (direction ) {

		case NORTH :
			y_robot ++ ;
			break ;

		case EAST :
			x_robot ++ ;
			break ;

		case SOUTH :
			y_robot -- ;
			break ;

		case WEST :
			x_robot -- ;
			break ;
		}
		return WHITE ;
	}

	int trouve_notre_couleur () {
		for ( int i = 0 ; i < hash_surface.size() ; i++  ) {

			if ( hash_surface.get(i).getX() == x_robot  && hash_surface.get(i).getY() == y_robot ) {
				// nous sommes sur une syrface connue
				return hash_surface.get(i).getCouleur();
				//trouve = true ;
				//break;		// pas la peinr de continuer
			} // mm x et y  
		} // booucle for
		return BLACK ;
	}

	void trouve_surface () {

		//int 	i = 0 ; // la cle 
		//S11_surface carre ;
		// hash_surface.forEach((i , carre) -> {
		
		for ( int i = 0  ; i <hash_surface.size() ; i++   ) {
			 if ( hash_surface.get(i).getX() < min_x ) min_x =  hash_surface.get(i).getX() ;
			 if ( hash_surface.get(i).getY() < min_y ) min_y =  hash_surface.get(i).getY() ;
			 if ( hash_surface.get(i).getX() > max_x ) max_x =  hash_surface.get(i).getX() ;
			 if ( hash_surface.get(i).getY() > max_y ) max_y =  hash_surface.get(i).getY() ;
		}
		
		System.out.println("les bords ");
		System.out.println("min_x = " + min_x )  ;
		System.out.println("max_x = " + max_x )  ;
		System.out.println("min_y = " + min_y )  ;
		System.out.println("max_y = " + max_y )  ;
		
		
		
	}	// end trouve_surface
	

	void visu_final ( ) {
		int couleur = 0 ;
		
		for ( int y = max_y ; y >= min_y ; y -- ) {
			//	pour la hauteur
			// System.out.println(" y = "  + y );
			for ( int x = min_x ; x <= max_x ; x ++ ) {
				// recherche dans la table d'un carre correspondant
				couleur = WHITE;
				for ( int i = 0  ; i <hash_surface.size() ; i++   ) {
					if ( hash_surface.get(i).getX() == x  && hash_surface.get(i).getY() == y ) {
						// cette zone a ete coloree
						couleur = hash_surface.get(i).getCouleur() ;
					} else {
						// au debut tout est noir
						//couleur = WHITE;
					}
				}       // end hash_surface.size()
				if (couleur == BLACK) {
					System.out.print(" "  );
				} else {
					System.out.print("#"  );
				}
			}		// fin de ligne
			System.out.println("--------"  );		// passage a la ligne apres lecture ligne
		}			// fin des lignes
		
		
//		System.out.println("/////////////-------- visu hash map "  );		// passage a la ligne apres lecture ligne
//		for ( int i = 0  ; i <hash_surface.size() ; i++   ) {
//			System.out.println("key = "  + i + " x =  " +  hash_surface.get(i).getX()  + " y = " +  hash_surface.get(i).getY()  
//					+ " couleur = " +  hash_surface.get(i).getCouleur());
//		}
	}		// visu_final
} // end class
