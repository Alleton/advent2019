package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*; 

import model.S10_asteroid; 



public class Solver10 {
	final int WIDTH = 35;
	final int HEIGHT= 35;
	HashMap <Integer, S10_asteroid> hash_asteroid = new  HashMap <Integer ,S10_asteroid> ();

	int[][] carte = new int [WIDTH][HEIGHT];
	int line_number = 0 ;
	int nb_aster = 0; 
	int max_vu = 0;

	int STATION = 263 ;  /// resultat de la part 1 
	int x_station  ;
	int y_station  ;



	String solver10 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019 day 10" );
		System.out.println("filename = " + sfname );

		//	initialize data
		init (sfname) ;
		// visu champ aster
		visu() ;

		// remplissage hastable des asteroids
		int vu =test_asteroid(STATION);

		System.out.println(" appel laser " );
		laser () ;

		return ("ok " + vu) ;

	} // constructeur 

	/*
	 * la methode du laser
	 */
	void laser () {
		// tant que l'on trouve une cible ..
		// creer la linkedhashmap
		LinkedHashMap<Integer, Integer> linked_map = new LinkedHashMap<>();
		// on rempit la linked avec tous les elemts visibles et existants
		// en commencant par le plus petit angle

		boolean trouve = true ;		// pas trouve d'element a inserer
		int nb_destruction = 0 ;
		int element = 0 ; 

		while ( (nb_destruction <=  201 )  || (  nb_destruction <  ( nb_aster  - 1 ) ) ) {
			System.out.println(" appel laser nb_destruction = " + nb_destruction  );
			while ( trouve) {
				trouve = false ;           // permets de sorti si on ne trouve rien
				double angle_maxi =  ( 2.0 *  Math.PI ) ;  // range par odre d'angle

				for (int i = 1 ; i<= nb_aster ; i++ ) {
					if ( i != STATION ) {
						S10_asteroid asteroid_i = hash_asteroid.get(i) ;
						if ( asteroid_i.isExiste() && asteroid_i.isVu() && asteroid_i.getAngle() <= angle_maxi) {
							// l'asteroid i n'est pas detruit , il est visible et son angle est petit
							angle_maxi = asteroid_i.getAngle();   // l ' angle diminu
							trouve = true;                        // au moins un element a inserer
							element = i ;                         // et c'est le i
						}  // fin if
						
					}
				}		// fin boucle 
				// retirons cet element de ceux existants et ajoutons le a la linked_map
				// si on a trouve 
				if (trouve )  {
					S10_asteroid asteroid_elem = hash_asteroid.get(element) ;
					asteroid_elem.setExiste(false);               // dommage !!
					linked_map.put(nb_destruction, element) ;
					nb_destruction = nb_destruction + 1 ;
					System.out.println(" destruction   nb_destruction = " + nb_destruction + " asteroid  = " + element + " at x = " +
							asteroid_elem.getX() + " y = " + asteroid_elem.getY() );	 
					
				}   // trouve ==> destruction
				
			} 		// trouve true
			System.out.println("fin  destruction   ");
			// remplissage hastable des asteroids
			int vu =test_asteroid(STATION);                    // on recherche de nouveau les visibles
			System.out.println("vu  des nouveaux asteroids  =    " + vu);
			// Runtime.getRuntime().halt(-1);
			// arret si rien
			if ( vu < 1) {
				 Runtime.getRuntime().halt(-1);
			}  // plus rien a voir
			else {
				trouve = true ;         //  on boucle sur le test
			}
	
		}
	

	} // en method laser


	void visu() {
		//	System.out.println(" fin ligne HEIGHT" );
		for (int i = 0 ; i <HEIGHT ; i++) {
			for (int j = 0 ; j <WIDTH ; j++) {
				if ( carte[j][i] != 0 ) {
					System.out.print("#" ); 
				} else {
					System.out.print("." );
				}
			}
			System.out.println(" fin ligne" );
		}
		
		for ( int i = 1 ; i <=  hash_asteroid.size() ; i++) {
			System.out.println(" asteroid  i " + i  + " x = " +  
					hash_asteroid.get(i).getX() +  " y = " +  hash_asteroid.get(i).getY() );
		}
		System.out.println("  ");
		System.out.println(" nb asteroids  nb_aster =  " + nb_aster ) ;
		System.out.println(" nb asteroids  hash_asteroid.size() =  " + hash_asteroid.size()  ) ;
		
		System.out.println("  ");
		System.out.println("  ");
		
		
		
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
						carte [i][line_number] = nb_aster ;
						hash_asteroid.put(nb_aster,  new S10_asteroid(nb_aster ,     i , line_number )) ;
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

			/*
			 * nouveau max nb_aster = 210
				pour asteroid  = 206
				pour meilleur_asteroid  = 11 et  = 13
			 */
			// la station est l'asteroid resultat de la part 1 
			S10_asteroid station = hash_asteroid.get(STATION) ;
			x_station = station.getX() ;
			y_station = station.getY() ;
			System.out.println("test_asteroid "  + STATION +  " x_station " +
					x_station + " y_station " + y_station); 


		} catch (IOException e) {
			// 
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}

	}

	int test_asteroid(int aster_number ) {
		// les coordonnees du depart


		int vu = 0 ;  
		System.out.println("");

		System.out.println("test_asteroid "  + aster_number );

		//System.out.println("test_asteroid x =  " + asteroid.getX() ); 
		// System.out.println("test_asteroid y =  " + asteroid.getY() );


		// voir tous les autres
		for (int i = 1 ; i<= nb_aster ; i++ ) {
			if ( i != aster_number )
				if  (test_visu ( aster_number , i  ) ) {
					vu = vu + 1 ;
					System.out.println("test_asteroid "  + aster_number + " vu  " + i );
				}
		}

		System.out.println(" vu done =  " + vu);


		// faire un tour complet



		return vu ;
	} // end test_asteroid

	int tour_laser () {


		return 0 ;
	}


	/*
	 * test visibilite aster(i) aster(j)
	 */
	boolean test_visu ( int i , int j ) {

		//System.out.println("test_visu "  + j );
		boolean succes = true;
		S10_asteroid asteroid2 = hash_asteroid.get(j) ;
		int x2 =asteroid2.getX() ;
		int y2 =asteroid2.getY() ;
		
		if ( ! asteroid2.isExiste()   ) return false ;
		
		
		//	asteroid i
		S10_asteroid asteroid1 = hash_asteroid.get(i) ;
		int x1 =asteroid1.getX() ;
		int y1 =asteroid1.getY() ;
		//System.out.println("voir asteroid " + i + " a x1 = " + x1 + " y1 = " + y1  ) ;

		
		// System.out.println("voir asteroid " + j + " a x2 = " + x2 + " y2 = " + y2  ) ;

		// si meme x on cherche un asteroid entre les deux
		if ( x2 == x1 ) {
			// les bornes en y
			succes= test_visu_x_constant ( i ,  j , x1 ,  y1 ,  y2) ;
			if ( succes ) {
				asteroid2.setVu(true) ;
			}
		}  else {
			// pas le mm x
			// le meme y ??
			//test_visu_y_constant (int i , int j ,int x1 , int x2 , int y) {
			if (y1 == y2 ) {
				succes= test_visu_y_constant ( i ,  j , x1 ,  x2 ,  y1) ;
				if ( succes ) {
					asteroid2.setVu(true) ;
				}
			} else {
				// pas les memes
				// si diff de 1  alors toujours visible
				if (( Math.abs(x1 - x2 ) == 1  ) || ( Math.abs(y1 - y2 ) == 1 )) {
					// System.out.println("diff de 1  x =  "  +  x1  + " et x 2 =  " + x2  + " ou entre y   " + y1 + " et " + y2 );
					succes = true;
					if ( succes ) {
						asteroid2.setVu(true) ;
					}

				} else {
					succes =  test_visu_xy ( i , j , x1 ,  x2 ,  y1 ,  y2) ;
					if ( succes ) {
						asteroid2.setVu(true) ;
					}

				}
			}
		}
		asteroid2.set_angle(x_station, y_station); 
		System.out.println ("" +  j +  " at x = " + asteroid2.getX() 
				+  " et y = " + asteroid2.getY() 
				+ " angle = " + asteroid2.getAngle()   + " en degres = " + 
				asteroid2.getAngle() * 365  /  (2 * Math.PI)) ;

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
				if ((asteroid_test.isExiste() ) && asteroid_test.getX() == x ) {
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
				if ( (asteroid_test.isExiste() ) &&  asteroid_test.getY() == y ) {
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
				if ( (asteroid_test.isExiste() ) ) {
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
					
				}

			}     // pas de else si un des asteroid a tester
		}        // fin boucle sur les copains
		return succes;
	}   // test_visu_y_constant


}// end class
