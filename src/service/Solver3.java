package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Solver3 {
	public static final int GRID_SIZE = 500000;
	//int  [][] grid ;
	int[] grid =new int  [GRID_SIZE];        	// notre grid wire 2 *  1000  row and 1000 column
	int Manhattan = GRID_SIZE * 2 ;								// maxy Manhattan distance
	int distance ;
	
	// int []  position ;								// notre position sur la grid
	int wire_length = - 1  ; 

	String solver3 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019" );
		System.out.println("filename = " + sfname );

		// fixe le polt central 
//int []  central_position = {GRID_SIZE/2,GRID_SIZE/2};						// position du pod central
		int []  central_position = {0,0 };						// position du pod central
//		int []  position = {GRID_SIZE/2,GRID_SIZE/2};								// position de depart
		int []  position = {0,0};								// position de depart

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
				
				String[] parts =line.split(","); // decompose la ligne en coordonees

				for ( int i = 0 ;  i < parts.length; i++) {
					direction = parts[i].substring(0,1) ;
					longueur  = Integer.parseInt(parts[i].substring(1) );
					
					
					System.out.println("direction " + direction + " " + longueur); 
					
					
					switch (direction) {
					case "R" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [0] = position [0] + 1 ;
							wire_length = wire_length + 1 ; 
							grid[wire_length] = (position [0] * 1000000 + position [1]);	
							}
						break;
					case "L" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [0] = position [0] - 1 ;
							wire_length = wire_length + 1 ; 
							grid[wire_length] = (position [0] * 1000000 + position [1]);
							}
						break;
					case "U" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [1] = position [1] + 1 ;
							wire_length = wire_length + 1 ; 
							grid[wire_length] = (position [0] * 1000000 + position [1]);
							}
						break;
					case "D" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [1] = position [1] - 1 ;
							wire_length = wire_length + 1 ; 
							grid[wire_length] = (position [0] * 1000000 + position [1]);
							}
						break;
					}
					
					
				}

				// lecture ligne 2 
			
				line= reader.readLine() ;
				System.out.println("wire 2 " ); 
				line = line.trim() ;
				// depart sur le plot central (  5000 , 5000 ) 
				position [0] = central_position [0]  ;
				position [1] = central_position [1]  ;


				//grid[wire][position [0]][position [0]] = 1; // le plot cenral est occupee


				String[] parts2 =line.split(","); // decompose la ligne en coordonees

				for ( int i = 0 ;  i < parts2.length; i++) {
					direction = parts2[i].substring(0,1) ;
					longueur  = Integer.parseInt(parts2[i].substring(1) );
					
					
					System.out.println("direction " + direction + " " + longueur); 
					switch (direction) {
					case "R" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [0] = position [0] + 1 ;
							// test intersection
							for ( int k = 1 ; k< grid.length ; k++ ) {
								if ( grid[k] == (position [0] * 1000000 + position [1]) ) {
									// le fil precedent y passe
									System.out.println("intersection "  + position [0] + " " +position [1]);
									distance = Math.abs(position [0] - central_position [0]  ) +  Math.abs(position [0] - central_position [0] );
									System.out.println("intersection "  + position [0] + " " +position [1] 
											+ " distance " + distance ) ;
								}
							}
						
							}
						break;
					case "L" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [0] = position [0] - 1 ;
							// test intersection
							for ( int k = 0 ; k< grid.length ; k++ ) {
								if ( grid[k] == (position [0] * 1000000 + position [1]) ) {
									// le fil precedent y passe
									System.out.println("intersection "  + position [0] + " " +position [1]);
									distance = Math.abs(position [0] - central_position [0]  ) +  Math.abs(position [0] - central_position [0] );
									System.out.println("intersection "  + position [0] + " " +position [1] 
											+ " distance " + distance ) ;
								}
							}
						
							}
						break;
					case "U" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [1] = position [1] + 1 ;
							// test intersection
							for ( int k = 0 ; k< grid.length ; k++ ) {
								if ( grid[k] == (position [0] * 1000000 + position [1]) ) {
									// le fil precedent y passe
									System.out.println("intersection "  + position [0] + " " +position [1]);
									distance = Math.abs(position [0] - central_position [0]  ) +  Math.abs(position [0] - central_position [0] );
									System.out.println("intersection "  + position [0] + " " +position [1] 
											+ " distance " + distance ) ;
								}
							}
						
							}
						break;
					case "D" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [1] = position [1] - 1 ;
							// test intersection
							for ( int k = 0 ; k< grid.length ; k++ ) {
								if ( grid[k] == (position [0] * 1000000 + position [1]) ) {
									// le fil precedent y passe
									System.out.println("intersection "  + position [0] + " " +position [1]);
									distance = Math.abs(position [0] - central_position [0]  ) +  Math.abs(position [1] - central_position [1] );
									System.out.println("intersection "  + position [0] + " " +position [1] 
											+ " distance " + distance ) ;
									if (distance < Manhattan  ) {
										Manhattan = distance ;
									}
								}
							}
						
							}
						break;
					}
			
				}
			
			
			
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
