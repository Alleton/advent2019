package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Solver3 {
	public static final int GRID_SIZE = 10000;
	//int  [][] grid ;
	int[][][] grid =new int [2][GRID_SIZE] [GRID_SIZE];        	// notre grid wire 2 *  1000  row and 1000 column
	int Manhattan = GRID_SIZE * 2 ;								// maxy Manhattan distance
	int distance ;
	
	// int []  position ;								// notre position sur la grid
	int wire = 0 ;  								// numero du fil 

	String solver3 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019" );
		System.out.println("filename = " + sfname );

		// fixe le polt central 
		int []  central_position = {GRID_SIZE/2,GRID_SIZE/2};						// position du pod central
		int []  position = {GRID_SIZE/2,GRID_SIZE/2};								// position de depart

		int x =0; 									// x coordinate
		int y =0; 									// y coordinate

		String direction ;							// direction du wire
		int longueur ;								// longuere dans cette direction
		String line = null;


		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			while ((line = reader.readLine()) != null) {
				System.out.println("wire " + wire); 
				line = line.trim() ;
				// depart sur le plot central (  5000 , 5000 ) 
				position [0] = central_position [0]  ;
				position [1] = central_position [1]  ;


				//grid[wire][position [0]][position [0]] = 1; // le plot cenral est occupee


				String[] parts =line.split(","); // decompose la ligne en coordonees

				for ( int i = 0 ;  i < parts.length; i++) {
					direction = parts[i].substring(0,1) ;
					longueur  = Integer.parseInt(parts[i].substring(1) );
					System.out.println("direction " + direction + " " + longueur); 
					switch (direction) {
					case "R" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [0] = position [0] + 1 ;
							grid[wire][position [0] ][position [1]] = 1;
						
							}
						break;
					case "L" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [0] = position [0] - 1 ;
							grid[wire][position [0] ][position [1]] = 1;
						
							}
						break;
					case "U" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [1] = position [1] + 1 ;
							grid[wire][position [0] ][position [1]] = 1;
						
							}
						break;
					case "D" :
						for ( int j = 1 ; j <= longueur ;  j ++ ) {
							position [1] = position [1] - 1 ;
							grid[wire][position [0] ][position [1]] = 1;
						
							}
						break;
						
						
					}
				}


				wire = wire + 1 ;
			}
			reader.close();
			
			System.out.println("grid mise a jour" );
			
			// exploration de la grid
			for ( int i= 0 ; i < GRID_SIZE ; i++ ) {
				for ( int j = 0 ; j < GRID_SIZE ; j ++ ) {
					// si croisement
					if ( (grid[0][i][j] == 1) && (grid[1][i][j] == 1 )  ) {
						distance = Math.abs((i - GRID_SIZE/2) ) + Math.abs((j - GRID_SIZE/2) ) ;
						if ( Manhattan > distance ) {
							Manhattan =distance ;
							System.out.println( "Manhattan " + Manhattan + "i = " + i  + " j = " + j  ) ;
						}
					}
				}
				
				
			}
			
			
			
			return " done paire = " ;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
	}
}
