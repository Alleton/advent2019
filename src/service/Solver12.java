package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;





import model.Moon;

public class Solver12 {

	String io = "io" ;
	// noms des lunes	
	String[] les_lunes = { "Io","Europa", "Ganymede", "Callisto" };	
	// la map des lunes
	// la map de la surface
	HashMap <Integer, Moon> map_lunes = new  HashMap <Integer ,Moon > ();
	Vector<String > state = new Vector<>();
	int stepe = 0 ;
	String position_initiale ="";
	String [] positions_ini  = new String [3];

	String notre_position ;
	String [] nos_positions = new String [3];

	int [] gagnes = {0,0,0};

	String solver12 (String sfname) {
		lecture (sfname) ; // lecture probleme
		voir_positions() ; // visu lecture
		position_initiale = memorize_positions() 	; // memorize le state ini

		for ( int axe = 0 ; axe <3 ; axe ++){
			positions_ini[axe] = memorize_positions_axe(  axe) ;
		}

		System.out.println("position_initiale  " + position_initiale);

		/*
		 * chaque dimension est independante
		 * la periode du system est le ppcm des periodes des 3 axes
		 */
		int step = 0 ;
		while ( ( gagnes[0] *gagnes[1] * gagnes[2]  == 0 )) {
			step = step + 1 ;
		//}
		//for (int step = 1 ; step < 3001 ; step++ ) {
			stepe = step;
			//			System.out.println(" ");
			//			System.out.println(" step " + step ) ;
			//			System.out.println(" ");
			//simulate ()      		; // simulation

			simulate_axe (0)      		; // simulation
			simulate_axe (1)      		; // simulation
			simulate_axe (2)      		; // simulation

			new_position ()  		; // recherche nouvelle position
			//voir_positions() 		; // reqsultat de la simulation
			notre_position= memorize_positions() 	; // memorize le state

			for ( int axe = 0 ; axe <3 ; axe ++){
				if ( gagnes[axe] == 0 ) {
					nos_positions[axe] = memorize_positions_axe(  axe) ;
					if ( nos_positions[axe].equals(positions_ini[axe]) ) {
						System.out.println(" step " + step ) ;
						System.out.println(" Gagne axe " + axe  ) ;
						gagnes[axe] = step ;	
					}
				}
			}

			//String memorize_positions_axe( int axe) {


			if ( notre_position.equals(position_initiale))    {
				System.out.println(" step " + step ) ;
				System.out.println(" Gagne "  ) ;
				break ;
			}

			//view_state ()    		; // voir les positions
			// show_energy()    ; // voir les energies
		}

		if ( gagnes[0] *gagnes[1] * gagnes[2]  != 0 ) {
			System.out.println( " avec calcul ppcm " );
			
			System.out.println(" ppcm 0 et 1  = " + ppcm(gagnes[0],gagnes[1] ));
			System.out.println(" ppcm 0 et 2  = " + ppcm(gagnes[0],gagnes[2] ));
			
			System.out.println( ppcm(gagnes[2],ppcm(gagnes[0],gagnes[1] )) );
			
			System.out.println(" avec valeurs ppcm 0 et 1  = " + ppcm(186028,193052 ));
			
			//System.out.println(" avec valeurs ppcm 0 et 1  et 2 = " + ppcm(388 334 772, 283 577 424 ));
			
		}

		
		return "solver12 ";
	}

	void simulate_axe (int axe) {
		// simulate effect of gravity
		// pour chaque lune
		for ( int lune = 0 ; lune<les_lunes.length ; lune ++ ) {
			// System.out.println(" simulate lune  = " +   lune +  " " + les_lunes[lune]);
			simulate_lune_axe ( lune , axe) ;
		}
	}

	void simulate_lune_axe ( int ma_lune , int axe ) {

		// switch en fonction axes
		switch (axe) {
		case 0 :
			int x0 = map_lunes.get(ma_lune).getX() ;
			for ( int lune = 0 ; lune<les_lunes.length ; lune ++ ) {
				if ( lune  != ma_lune ) {
					// pas de gravitation avec nous meme
					//int dx = 
					if ( x0 < map_lunes.get(lune).getX()) {
						map_lunes.get(ma_lune).setVx( map_lunes.get(ma_lune).getVx() + 1 );
					}
					if ( x0 > map_lunes.get(lune).getX()) {
						map_lunes.get(ma_lune).setVx( map_lunes.get(ma_lune).getVx() - 1 );
					}
				}			

			}
			break ;

		case 1 : 
			int y0 = map_lunes.get(ma_lune).getY() ;
			for ( int lune = 0 ; lune<les_lunes.length ; lune ++ ) {
				if ( lune  != ma_lune ) {
					// pas de gravitation avec nous meme
					//int dx = 
					// dy
					if ( y0 < map_lunes.get(lune).getY()) {
						map_lunes.get(ma_lune).setVy( map_lunes.get(ma_lune).getVy() + 1 );
					}
					if ( y0 > map_lunes.get(lune).getY()) {
						map_lunes.get(ma_lune).setVy( map_lunes.get(ma_lune).getVy() - 1 );
					}

				}  //  lune  != ma_lune
			}		// for lube
			break ;
		case 2 :
			int z0 = map_lunes.get(ma_lune).getZ() ;
			for ( int lune = 0 ; lune<les_lunes.length ; lune ++ ) {
				if ( lune  != ma_lune ) {
					// pas de gravitation avec nous meme
					if ( z0 < map_lunes.get(lune).getZ()) {
						map_lunes.get(ma_lune).setVz( map_lunes.get(ma_lune).getVz() + 1 );
					}
					if ( z0 > map_lunes.get(lune).getZ()) {
						map_lunes.get(ma_lune).setVz( map_lunes.get(ma_lune).getVz() - 1 );
					}

				}  //  lune  != ma_lune
			}		// for lube
			break ;
		}
	}

	void simulate () {
		// simulate effect of gravity
		// pour chaque lune
		for ( int lune = 0 ; lune<les_lunes.length ; lune ++ ) {
			// System.out.println(" simulate lune  = " +   lune +  " " + les_lunes[lune]);
			simulate_lune ( lune ) ;
		}
	}

	void simulate_lune ( int ma_lune ) {
		int x0 = map_lunes.get(ma_lune).getX() ;
		int y0 = map_lunes.get(ma_lune).getY() ;
		int z0 = map_lunes.get(ma_lune).getZ() ;
		for ( int lune = 0 ; lune<les_lunes.length ; lune ++ ) {
			if ( lune  != ma_lune ) {
				// pas de gravitation avec nous meme
				//int dx = 
				if ( x0 < map_lunes.get(lune).getX()) {
					map_lunes.get(ma_lune).setVx( map_lunes.get(ma_lune).getVx() + 1 );
				}
				if ( x0 > map_lunes.get(lune).getX()) {
					map_lunes.get(ma_lune).setVx( map_lunes.get(ma_lune).getVx() - 1 );
				}
				// dy
				if ( y0 < map_lunes.get(lune).getY()) {
					map_lunes.get(ma_lune).setVy( map_lunes.get(ma_lune).getVy() + 1 );
				}
				if ( y0 > map_lunes.get(lune).getY()) {
					map_lunes.get(ma_lune).setVy( map_lunes.get(ma_lune).getVy() - 1 );
				}
				// dz
				if ( z0 < map_lunes.get(lune).getZ()) {
					map_lunes.get(ma_lune).setVz( map_lunes.get(ma_lune).getVz() + 1 );
				}
				if ( z0 > map_lunes.get(lune).getZ()) {
					map_lunes.get(ma_lune).setVz( map_lunes.get(ma_lune).getVz() - 1 );
				}

			}  //  lune  != ma_lune
		}		// for lube
	}			// end method simule


	void new_position (){
		for ( int lune = 0 ; lune<les_lunes.length ; lune ++ ) {
			map_lunes.get(lune).setX(map_lunes.get(lune).getX() + map_lunes.get(lune).getVx());
			map_lunes.get(lune).setY(map_lunes.get(lune).getY() + map_lunes.get(lune).getVy());
			map_lunes.get(lune).setZ(map_lunes.get(lune).getZ() + map_lunes.get(lune).getVz());
		}
	}

	void MemorizePosition (){

	}


	void show_energy() {
		int total = 0 ;
		for ( int i = 0 ; i < les_lunes.length ; i ++ ) {
			System.out.println("energy  "+ i + " " + map_lunes.get(i).energy());
			total = total + map_lunes.get(i).energy() ;
			System.out.println("energy  totale "+ total );
		}
	}

	// lecture probleme
	String lecture (String sfname) {
		String line ;
		int    numero_lune = 0;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			// boucle sur les lunes
			while ((line = reader.readLine()) != null) {
				Moon cette_lune = new Moon ( les_lunes[numero_lune]) ;

				// decomposition ligne
				//String temp[] = line.replaceAll("[^\\d.,]", "").split(",") 
				String temp[] = line.split(",") ;
				for ( int i = 0 ; i < temp.length ; i++ ) {
					temp[i] = temp[i].replaceAll("[^\\d.-]", "") ;

					// mise en place des donnees
					switch (i) {
					case 0 :
						cette_lune.setX( Integer.parseInt(temp[i]) ) ;
						break;
					case 1 :
						cette_lune.setY( Integer.parseInt(temp[i]) ) ;
						break;

					case 2 :
						cette_lune.setZ( Integer.parseInt(temp[i]) ) ;
						break;
					}

				}
				// ajouter aux lunes
				// ajouter la vitesse nulle a la position
				map_lunes.put(numero_lune, cette_lune) ;
				numero_lune = numero_lune + 1 ;
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
		System.out.println( position_initiale );
		return "lecture OK " ;

	}   // end method lecture

	void voir_positions() {
		for ( int i = 0  ; i< map_lunes.size() ; i++ ) {
			map_lunes.get(i ).visu_lune(); 
		}

	}  // end voir_positions

	String memorize_positions_axe( int axe) {
		String positions = "";
		for ( int i = 0  ; i< map_lunes.size() ; i++ ){
			// 	public String mem_pos () {
			positions = positions.concat(map_lunes.get(i).mem_pos_axe(axe)) ;

		} // end for
		// state
		//System.out.println( positions );
		//state.add(positions) ;
		return positions ;
	}		// end method memorize


	String memorize_positions() {
		String positions = "";
		for ( int i = 0  ; i< map_lunes.size() ; i++ ){
			// 	public String mem_pos () {
			positions = positions.concat(map_lunes.get(i).mem_pos()) ;

		} // end for
		// state
		//System.out.println( positions );
		//state.add(positions) ;
		return positions ;
	}		// end method memorize

	boolean check_position () {

		return false ;
	}


	void view_state () {
		for ( int i = 0 ; i<= stepe  ; i++ ) {
			System.out.println(" state " + state.get(i) );
		}
	}


	long  ppcm(long a,long b){
		long p=a*b;
		while (a!=b) if (a<b) b-=a; else a-=b;
		return p/a;
	}


}
