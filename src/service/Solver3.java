package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import model.S3_point;;


public class Solver3 {
	
	
	public static final int GRID_SIZE = 500000;
	//int  [][] grid ;
	
	S3_point[] grid =new S3_point  [GRID_SIZE];        	// notre grid wire 2 *  1000  row and 1000 column
	
	int Manhattan = GRID_SIZE * 2 ;								// maxy Manhattan distance
	int Manhattan2 = GRID_SIZE * 2 ;								// maxy Manhattan distance
	int distance ;
	
	// int []  position ;								// notre position sur la grid
	int wire_length = - 1  ; 
	int wire2_length = - 1  ;
	
	int x = 0 ;
	int y = 0 ;
	
	int x_wire = 0 ;
	int y_wire = 0 ;
	
	// part 2 
	String solver3 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019" );
		System.out.println("filename = " + sfname );

		//	origine
		S3_point Wire2 = new S3_point(0,0);
		
		String direction ;							// direction du wire
		int longueur ;								// longuere dans cette direction
		String line = null;


		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			line = reader.readLine() ;
				System.out.println("wire 1 " ); 
				line = line.trim() ;
				
				x_wire = 0 ;
				y_wire = 0 ;
				
				String[] parts =line.split(","); // decompose la ligne en coordonees

				for ( int i = 0 ;  i < parts.length; i++) {
					direction = parts[i].substring(0,1) ;
					longueur  = Integer.parseInt(parts[i].substring(1) );
					
					
					System.out.println("direction " + direction + " " + longueur); 
					
					
					switch (direction) {
					case "R" :
						x = 1 ;
						y = 0 ;
						break;
					case "L" :
						x = -1 ;
						y = 0 ;
						break;
					case "U" :
						x = 0 ;
						y = 1 ;
						break;
					case "D" :
						x = 0 ;
						y =  -1 ;
						break;
					}
					
					for ( int j = 1 ; j <= longueur ;  j ++ ) {
						wire_length = wire_length + 1 ; 
						x_wire = x_wire + x ;
						y_wire = y_wire + y ;
						// System.out.println("wire_length = " + wire_length  ); 
						grid[wire_length] = new S3_point(x_wire,y_wire) ; 	
						}
					
				}

				// lecture ligne 2 
			
				line= reader.readLine() ;
				System.out.println("wire 2 " ); 
				line = line.trim() ;

				// depart sur le plot central (  5000 , 5000 ) 
				x_wire = 0 ;
				y_wire = 0 ;


				//grid[wire][position [0]][position [0]] = 1; // le plot cenral est occupee


				String[] parts2 =line.split(","); // decompose la ligne en coordonees

				for ( int i = 0 ;  i < parts2.length; i++) {
					direction = parts2[i].substring(0,1) ;
					longueur  = Integer.parseInt(parts2[i].substring(1) );
					
					
					// System.out.println("direction " + direction + " " + longueur); 
					switch (direction) {
					case "R" :
						x = 1 ;
						y = 0 ;
						break;
					case "L" :
						x = -1 ;
						y = 0 ;
						break;
					case "U" :
						x = 0 ;
						y = 1 ;
						break;
					case "D" :
						x = 0 ;
						y =  -1 ;
						break;
					}
					for ( int j = 1 ; j <= longueur ;  j ++ ) {
						x_wire = x_wire + x ;
						y_wire = y_wire + y ;
						Wire2.setX(x_wire);
						Wire2.setY(y_wire);
						wire2_length = wire2_length + 1   ;
						//  System.out.println("wire_length = " + wire_length  );
						
						for ( int k = 1 ; k< wire_length  ; k++ ) {
							// System.out.println("wire_length = " + k  );
							//if ( grid[k].eqals())
							if ( grid[k].equals(Wire2 ) ) {
								System.out.println( " croisement   " + x_wire + " y = " + y_wire  + " eloignement = " + Wire2.eloignement()) ;
								System.out.println( " croisement   wire_length " + ( k +1  ) + " wire2_length = " + ( wire2_length +1 )  ) ;
								if ( Manhattan2 > ( k + wire2_length + 2 ) ) {
									 Manhattan2 = ( k + wire2_length + 2 ) ;
								}
							} 	// equals
							
						} 		//    k< wire_length 
					}			//  j <= longueur 
					
					
					
					
				}				// i < parts2.length
			
			
			
			reader.close();
			
			System.out.println("grid mise a jour" );
			
			// exploration de la grid
			
			
			
			
			return " done paire = " + Manhattan2 ;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
	}

	// part 1 
	String solver3_1 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019" );
		System.out.println("filename = " + sfname );

		// fixe le polt central 
		int []  central_position = {0,0 };						// position du pod central
		int []  position = {0,0};								// position de depart

		// S3_point originOne = new S3_point(0,0);
		S3_point Wire2 = new S3_point(0,0);
		
		String direction ;							// direction du wire
		int longueur ;								// longuere dans cette direction
		String line = null;


		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			line = reader.readLine() ;
				System.out.println("wire 1 " ); 
				line = line.trim() ;
				// depart sur le plot central (  5000 , 5000 ) 
				position [0] = central_position [0]  ;
				position [1] = central_position [1]  ;
				
				x_wire = 0 ;
				y_wire = 0 ;
				
				String[] parts =line.split(","); // decompose la ligne en coordonees

				for ( int i = 0 ;  i < parts.length; i++) {
					direction = parts[i].substring(0,1) ;
					longueur  = Integer.parseInt(parts[i].substring(1) );
					
					
					System.out.println("direction " + direction + " " + longueur); 
					
					
					switch (direction) {
					case "R" :
						x = 1 ;
						y = 0 ;
						break;
					case "L" :
						x = -1 ;
						y = 0 ;
						break;
					case "U" :
						x = 0 ;
						y = 1 ;
						break;
					case "D" :
						x = 0 ;
						y =  -1 ;
						break;
					}
					
					for ( int j = 1 ; j <= longueur ;  j ++ ) {
						wire_length = wire_length + 1 ; 
						x_wire = x_wire + x ;
						y_wire = y_wire + y ;
						// System.out.println("wire_length = " + wire_length  ); 
						grid[wire_length] = new S3_point(x_wire,y_wire) ; 	
						}
					
				}

				// lecture ligne 2 
			
				line= reader.readLine() ;
				System.out.println("wire 2 " ); 
				line = line.trim() ;
				// depart sur le plot central (  5000 , 5000 ) 
				position [0] = central_position [0]  ;
				position [1] = central_position [1]  ;

				x_wire = 0 ;
				y_wire = 0 ;


				//grid[wire][position [0]][position [0]] = 1; // le plot cenral est occupee


				String[] parts2 =line.split(","); // decompose la ligne en coordonees

				for ( int i = 0 ;  i < parts2.length; i++) {
					direction = parts2[i].substring(0,1) ;
					longueur  = Integer.parseInt(parts2[i].substring(1) );
					
					
					System.out.println("direction " + direction + " " + longueur); 
					switch (direction) {
					case "R" :
						x = 1 ;
						y = 0 ;
						break;
					case "L" :
						x = -1 ;
						y = 0 ;
						break;
					case "U" :
						x = 0 ;
						y = 1 ;
						break;
					case "D" :
						x = 0 ;
						y =  -1 ;
						break;
					}
					for ( int j = 1 ; j <= longueur ;  j ++ ) {
						x_wire = x_wire + x ;
						y_wire = y_wire + y ;
						Wire2.setX(x_wire);
						Wire2.setY(y_wire);
						//  System.out.println("wire_length = " + wire_length  );
						
						for ( int k = 1 ; k< wire_length  ; k++ ) {
							// System.out.println("wire_length = " + k  );
							//if ( grid[k].eqals())
							if ( grid[k].equals(Wire2 ) ) {
								System.out.println( " croisement   " + x_wire + " y = " + y_wire  + " eloignement = " + Wire2.eloignement()) ;
								if ( Manhattan > Wire2.eloignement() ) {
									 Manhattan = Wire2.eloignement() ;
								}
							} 	// equals
							
						} 		//    k< wire_length 
					}			//  j <= longueur 
					
					
					
					
				}				// i < parts2.length
			
			
			
			reader.close();
			
			System.out.println("grid mise a jour" );
			
			// exploration de la grid
			
			
			
			
			return " done paire = " + Manhattan ;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
	}
}

