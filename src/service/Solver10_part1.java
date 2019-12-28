package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*; 

import model.S10_asteroid; 



public class Solver10_part1 {
	final int WIDTH = 35;
	final int HEIGHT= 35;
	HashMap <Integer, S10_asteroid> hash_asteroid = new  HashMap <Integer ,S10_asteroid> ();

	int[][] carte = new int [WIDTH][HEIGHT];
	int line_number = 0 ;
	int nb_aster = 0; 
	int max_vu = 0;




	String solver10 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019 day 10" );
		System.out.println("filename = " + sfname );

		//	initialize data
		init (sfname) ;
		// visu champ aster
		visu() ;

		// test visu aster n
		// 	int test_asteroid(int aster_number ) 
		for (int i = 1 ; i<=nb_aster ; i++ ) {
			int vu =test_asteroid(i);
			if (vu > max_vu) {
				max_vu = vu ;
				System.out.println("nouveau max nb_aster = " + max_vu );
				System.out.println("pour asteroid  = " + i  );
				S10_asteroid meilleur_asteroid = hash_asteroid.get(i) ;
				System.out.println("pour meilleur_asteroid  = " + meilleur_asteroid.getY() + " et  = " + meilleur_asteroid.getX()  );
			}
		}

		return ("ok") ;

	}
	void visu() {
		//	System.out.println(" fin ligne HEIGHT" );
		for (int i = 0 ; i <HEIGHT ; i++) {
			for (int j = 0 ; j <WIDTH ; j++) {
				if ( carte[i][j] != 0 ) {
					System.out.print("#" ); 
				} else {
					System.out.print(" " );
				}
			}
			System.out.println(" fin ligne" );
		}
	} // end visu

	void init (String sfname) {
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			while ((line = reader.readLine()) != null) {
				//char[] ch = new char[line.length()]; 
				for (int i = 0 ; i< line.length() ; i++) {
					if (line.charAt(i) == '#') {
						nb_aster = nb_aster + 1  ;
						carte[line_number][i] = nb_aster ;
						hash_asteroid.put(nb_aster,  new S10_asteroid(nb_aster ,  line_number ,  i)) ;
						// S10_asteroid ( int n_aster , double nx , double ny)
						//S10_asteroid asteroid = new S10_asteroid(nb_aster , (double) line_number , (double) i)  ; 

					}
					else {
						carte[line_number][i] = 0 ;
					}
				}
				line_number = line_number + 1;
			}
			reader.close();
		} catch (IOException e) {
			// 
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}

	}

	int test_asteroid(int aster_number ) {
		// les coordonnees du depart
		double x0 = 0 ;
		double y0 = 0 ;

		// les coordonnees de l'autre
		double x1 = 0 ;
		double y1 = 0 ;

		int vu = 0 ;  
		System.out.println("");
		
		System.out.println("test_asteroid "  + aster_number );
		S10_asteroid asteroid = hash_asteroid.get(aster_number) ;
		//System.out.println("test_asteroid x =  " + asteroid.getX() ); 
		// System.out.println("test_asteroid y =  " + asteroid.getY() );

		x0 =asteroid.getX() ;
		y0 =asteroid.getY() ;

		// voir tous les autres
		for (int i = 1 ; i<= nb_aster ; i++ ) {
			if ( i != aster_number )
			if  (test_visu ( aster_number , i  ) ) vu = vu + 1 ;
		}

		System.out.println(" vu =  " + vu); 
		
			return vu ;
		} // end test_asteroid
	
	/*
	 * test visibilite aster(i) aster(j)
	 */
	boolean test_visu ( int i , int j ) {

		//System.out.println("test_visu "  + j );
		boolean succes = true;
		//	asteroid i
		S10_asteroid asteroid1 = hash_asteroid.get(i) ;
		int x1 =asteroid1.getX() ;
		int y1 =asteroid1.getY() ;
		//System.out.println("voir asteroid " + i + " a x1 = " + x1 + " y1 = " + y1  ) ;

		S10_asteroid asteroid2 = hash_asteroid.get(j) ;
		int x2 =asteroid2.getX() ;
		int y2 =asteroid2.getY() ;
		// System.out.println("voir asteroid " + j + " a x2 = " + x2 + " y2 = " + y2  ) ;
		
		// si meme x on cherche un asteroid entre les deux
		if ( x2 == x1 ) {
			// les bornes en y
			succes= test_visu_x_constant ( i ,  j , x1 ,  y1 ,  y2) ;
		}  else {
			// pas le mm x
			// le meme y ??
			//test_visu_y_constant (int i , int j ,int x1 , int x2 , int y) {
			if (y1 == y2 ) {
				succes= test_visu_y_constant ( i ,  j , x1 ,  x2 ,  y1) ;
			} else {
				// pas les memes
				// si diff de 1  alors toujours visible
				if (( Math.abs(x1 - x2 ) == 1  ) || ( Math.abs(y1 - y2 ) == 1 )) {
					// System.out.println("diff de 1  x =  "  +  x1  + " et x 2 =  " + x2  + " ou entre y   " + y1 + " et " + y2 );
					succes = true;
				} else {
					succes =  test_visu_xy ( i , j , x1 ,  x2 ,  y1 ,  y2) ;
				}
			}
					
					
		}
		
		return succes;
	}	// end test_visu
	
	boolean test_visu_x_constant (int i , int j ,int x , int y1 , int y2) {
		// System.out.println("test_visu_x_constant  x =  "  +  x  + " entre  " + y1 + " et " + y2 );

		boolean succes = true;
		
		int miny = Math.min(y1,y2 ) ;
		int maxy = Math.max(y1,y2 ) ;
		// on passe en revu les autres astrroid 
		for ( int  k = 1 ; k <= nb_aster ; k++ ) {
			// eliminons nous pour ne pas faire 2 test inutiles
			if ( k != i && k!=j ) {
				S10_asteroid asteroid_test = hash_asteroid.get(k) ;
				// meme x ?
				if ( asteroid_test.getX() == x ) {
					// y entre y1 et y2
					if ( asteroid_test.getY() > miny &&  asteroid_test.getY() < maxy ) succes = false ;
				} // pas de else si pas mm x
			}     // pas de else si un des asteroid a tester
		}        // fin boucle sur les copains
		return succes;
	}   // test_visu_x_constant
	
	boolean test_visu_y_constant (int i , int j ,int x1 , int x2 , int y) {
		// System.out.println("test_visu_y_constant  y =  "  +  y  + " entre  " + x1 + " et " + x2 );

		boolean succes = true;
		
		int minx = Math.min(x1,x2 ) ;
		int maxx = Math.max(x1,x2 ) ;

		// on passe en revu les autres astrroid 
		for ( int  k = 1 ; k <= nb_aster ; k++ ) {
			// eliminons nous pour ne pas faire 2 test inutiles
			if ( k != i && k!=j ) {
				S10_asteroid asteroid_test = hash_asteroid.get(k) ;
				// meme x ?
				if ( asteroid_test.getY() == y ) {
					// x entre x1 et x2
					if ( asteroid_test.getX() > minx &&  asteroid_test.getX() < maxx ) succes = false ;
				} // pas de else si pas mm x
			}     // pas de else si un des asteroid a tester
		}        // fin boucle sur les copains
		return succes;
	}   // test_visu_y_constant

	boolean test_visu_xy (int i , int j ,int x1 , int x2 , int y1 , int y2) {
		// System.out.println("test_visu_xy  y1 =  "  +  y1  + " entre  " + x1 + " et " + x2 );

		boolean succes = true;
		
		int minx = Math.min(x1,x2 ) ;
		int maxx = Math.max(x1,x2 ) ;
		int miny = Math.min(y1,y2 ) ;
		int maxy = Math.max(y1,y2 ) ;
		
		// on passe en revu les autres astrroid 
		for ( int  k = 1 ; k <= nb_aster ; k++ ) {
			// eliminons nous pour ne pas faire 2 test inutiles
			if ( k != i && k!=j ) {
				S10_asteroid asteroid_test = hash_asteroid.get(k) ;
				int x_test = asteroid_test.getX();
				int y_test = asteroid_test.getY();
				// meme x ?
				// dans le rectangle 
				if ( (x_test > minx) && ( x_test < maxx) && ( y_test > miny ) && ( y_test < maxy)) {
					// ( x1-x2 )  / ( y1 - y2)  == (  x1- x )  / ( y1 - y ) ==>
					// ( x1-x2 )  / ( y1 - y2)  == (  x1- x )  / ( y1 - y ) ==>
					if  ((  x1-x2 ) *  ( y1 - y_test )   == (  x1- x_test ) * ( y1 - y2) ) {
						succes = false ;
					}
				}
				
			}     // pas de else si un des asteroid a tester
		}        // fin boucle sur les copains
		return succes;
	}   // test_visu_y_constant
	

	}// end class
