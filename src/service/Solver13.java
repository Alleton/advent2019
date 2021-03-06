package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import model.ArcadeModel;
import model.IntcodePrg13;
import model.IntcodeProgam;
import model.S11_surface;
import view.V_test;
import view.arcade;
import view.V_arcade;
import controller.AdventController;
import controller.ArcadeController;
import model.Tile;



/**
 * 0 is an empty tile. No game object appears in this tile.
1 is a wall tile. Walls are indestructible barriers.
2 is a block tile. Blocks can be broken by the ball.
3 is a horizontal paddle tile. The paddle is indestructible.
4 is a ball tile. The ball moves diagonally and bounces off objects.
 * @author jf
 *
 */

public class Solver13 {

	String line ;
	long[] outputs = new long[3]; // pour stoker les 2 outputs
	int   outputs_numero = 0 ;
	final int INPUT = 3 ;
	final int OUTPUT = 4 ;
	final int EXIT  = 99 ;
	final int EMPTY = 0 ;
	final int WALL  = 1 ;
	final int BLOCK = 2 ;
	final int PADDLE = 3 ;
	final int BALL   = 4 ;

	boolean creation = true;


	int compte = 0 ;
	//int compte_id [] = new int [5] ;  // il y a 5 id de blocks
	int width = 0 ;		// largeur
	int height = 0 ;	// hauteur

	// la vue 
	int t_width = 42 ;		// largeur
	int t_height = 23 ;	// hauteur

	
	int x_ball = 0 ;	// position en x de la balle
	int x_pad  = 0 ;	// position en x du paddle
	
	long res_intcode ;

	// la map de creation
	HashMap <Integer, Tile> hash_entry = new  HashMap <Integer ,Tile> ();
	// la map resultat
	// HashMap <Integer, Tile> hash_arcade = new  HashMap <Integer ,Tile> ();
	int[][] ecran = new int[42][23] ;           // representation de l'ecran


	String solver13 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019 day 11" );
		System.out.println("filename = " + sfname );
		read_line( sfname) ;

		// initialize le tableau
		for ( int y = 0 ; y  <= 22 ; y++ ) {
			for ( int x = 0 ; x <= 41 ; x ++) ecran [x][y] =0 ;
		}

		
		outputs_numero = 0 ;
		int creations = 0 ;		// nombre de creations

		IntcodePrg13 ampliA = new IntcodePrg13 ( 0 ,1 , line , 2, 0 ) ;
		//IntcodePrg13 code_play = new IntcodePrg13 ( 0 ,1 , line , 2, 0 ) ;
		// mettre 2 quarters
		ampliA.add_quarter(2L);

		res_intcode = ampliA.start() ;

		System.out.println(" quarters =   " + ampliA.getInstruction(0)  ) ;		

		System.out.println("ampliA state  = " + ampliA.getAmplifier_state() );
		while (ampliA.getAmplifier_state()!=  EXIT) {
			// le micro code
			switch (ampliA.getAmplifier_state() ) {
			case OUTPUT :{
				System.out.println("outputs_numero  = " +outputs_numero );
				outputs [outputs_numero ] =  res_intcode;
				outputs_numero = outputs_numero + 1 ; /// suivant
				if ( creation ) {

					if ( outputs_numero > 2 ) {
						//
						//					paint_move( ( int) outputs[0]  , ( int)  outputs[1] ); // colori et bouge
						//					int notre_couleur  = trouve_notre_couleur();
						// 
						if (outputs[0] == -1 && outputs[1] == 0  ) {
							// fin creation , score
							if( creation ) compte_blocks () ;
							creation = false;
							System.out.println("score  = "  +outputs[2] )  ;
							outputs_numero = 0 ; // ben oui
							// break ;

						}else 
						{
							System.out.println("creation x = "  +  outputs[0]  + " y = " + outputs[1]  + "tile type " + outputs[2] );
							Tile debut = new Tile ((int) outputs[0] ,(int)  outputs[1] ,(int)  outputs[2] ) ;
							hash_entry.put(creations,  debut) ;
							creations = creations + 1 ;
							outputs_numero = 0 ; // ben oui

							//break ;
						} // pas score
					} else {
						// outputs_numero < 2
						//rien
					}  // test output num
				} else {
					// plus en mode creation
				if ( outputs_numero <= 2 ) {
						// memorisation seule  cad rien
					} else {
						if (outputs[0] == -1 && outputs[1] == 0  ) {
							System.out.println("score  = "  +outputs[2] )  ;
							outputs_numero = 0 ; // ben oui
						} //else change_tile (outputs[0], outputs[1] , outputs[2] ) ;
						else {
							// change tile
							// final int BALL   = 4 ;
							System.out.println("change tile x = "  + outputs[0] + " y = "  + outputs[1] + " idx = "  + outputs[2] )  ;
							change_tile ( (int)outputs[0],(int) outputs[1] ,(int) outputs[2] ) ;
							if ( outputs[2]== BALL ) { 
								x_ball = (int)outputs[0] ;
								System.out.println("x_ball = "  + x_ball + " y_ball = " +  outputs[1] )  ;
								// calcul du mvmt du joystick
								int joystick = Integer.signum (  x_ball - x_pad ) ;
								ampliA.setInput(joystick);
								// va dans cette direction
							}
							if ( outputs[2]== PADDLE ) { 
								x_pad = (int)outputs[0] ;
								System.out.println("x_pad = "  + x_pad )  ;
							}
							outputs_numero = 0 ; // ben oui
						}
					}
				}
				res_intcode= ampliA.re_start_output () ;     // dans tous les cas
				break ;

			}
			case INPUT :{
				// on n'set plus en creation 
				creation = false;
				/*
				 * The arcade cabinet has a joystick that can move left and right. The software reads the position of the joystick with input instructions:

				 * If the joystick is in the neutral position, provide 0.
				 * If the joystick is tilted to the left, provide -1.
				 * If the joystick is tilted to the right, provide 1.
				 */
				int joystick = Integer.signum (  x_ball - x_pad ) ;
				
				// 
				//compte_blocks () ;
				// 
				System.out.println( " INPUT en solver 13 ");
				System.out.println( " test_blocks =  " + test_blocks() );
				System.out.println( " joystick =  " + joystick );
				res_intcode =  ampliA.re_start (joystick) ;
				
				break ;
			}
			case EXIT :{
				// Runtime.getRuntime().halt (0) ;
				break ;
			}
			}		// switch intcode 

		}
		System.out.println( " compte blocks = " + compte_blocks () );

		return ("solver 13 ") ;

	} 		// end constructor String filename

	/*
	 * change_tile (outputs[0], outputs[1] , outputs[2] ) ;
	 */

	void change_tile  ( int x , int y , int id ) {
		// final int BALL   = 4 ;
		// modifie
		if ( ecran[  x][  y]  ==  BLOCK) {
			System.out.println( " change un bloc ");
		}
		
		ecran[  x][  y] =  id ;
		System.out.println( " ecran x = " + x + " y =  " + y + "   valeur " + ecran[  x][  y]  ) ;
		
	}
	
	



	int test_blocks (){
		int test = 0 ;
		for ( int y=0 ; y<23 ; y++) {
			for (int x = 0 ; x < 42 ; x++) {
				if (ecran[x][y]  == BLOCK ) test ++ ;
			} // for x
		}		// for y
		return test ;	
	}	// end test_blocks

	int compte_blocks () {
		//int compte = 0 ;
		int compte_id [] = new int [5] ;
		//	final int BLOCK = 2 ;
		boolean trouve = false ;
		int x ;
		int y ;
		int id ;

		for ( int i = 0  ; i < hash_entry.size() ; i ++ ) {
			// hash_arcade = hash_entry sans doublons
			trouve = false ;

			x = hash_entry.get(i).getX() ;
			y = hash_entry.get(i).getY() ;
			id = hash_entry.get(i).getType() ;

			ecran[x][y] = id ;

			if (width < x  ) width = x ;
			if (height < y  ) height = y ;
			//
		} // fin creation ecran

		/*
		 * 	int width = 0 ;		// largeur
		 *  int height = 0 ;	// hauteur
		 */
		for (  y=0 ; y<23 ; y++) {
			for ( x = 0 ; x < 42 ; x++) {
				compte_id [ecran[x][y] ] ++ ;
			}

		}


		for ( int i = 0 ; i < compte_id.length ; i ++ ) {
			System.out.println(" compte id =   " + i  + "  = "  + compte_id[i]   ) ;
		}
		System.out.println(" max x = " +  width ) ;
		System.out.println(" max y = " +  height ) ;


		return compte ;
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
}
