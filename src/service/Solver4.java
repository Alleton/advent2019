package service;

public class Solver4 {


	// input is 387638-919123.

	final int LOW = 387638 ;
	// LOW = 387638;
	final int HIGH = 919123 ;

	// (1) define your java int array
	int[] intArray = new int[] {1,2,3,4,5,6,7,8,9};
	int[] MYArray = new int[] {3,8,7,6,3,8};
	int pwd;
	int nb_pwd = 0 ;

	public String solver4 (String sfname){

		// boucle sur 10*6
		outer_loop :
			for ( int i1 = 3 ; i1 <10 ; i1 ++ )
			{
				System.out.println(" i1  " + i1 );	
				// boucle sur 10*5
				for ( int i2 = i1 ; i2 <10 ; i2 ++ ){
					// boucle sur 10*4
					System.out.println(" i2  " + i2 );
					for ( int i3 = i2 ; i3 <10 ; i3 ++ ){
						// boucle sur 10*3
						System.out.println(" i3  " + i3 );
						for ( int i4 = i3 ; i4 <10 ; i4 ++ ){
							// boucle sur 10*2
							System.out.println(" i4  " + i4 );
							for ( int i5 = i4 ; i5 <10 ; i5 ++ ){
								// boucle sur 10*1
								System.out.println(" i5  " + i5 );
								for ( int i6 = i5 ; i6 <10 ; i6 ++ ){
									// boucle sur 10*0
									System.out.println(" i6  " + i6 );
									pwd = i1 * 100000 + i2 * 10000 + 
											i3 * 1000  + i4 * 100 + 
											i5 * 10 + i6   ;
									// System.out.println(" pwd " + pwd );		
									if (pwd > HIGH ) break  outer_loop;
									if (pwd > LOW ) 
									{
										// test validite
										if ( i2 == i1 || i3 == i2 || i4 == i3 || i5 == i4 || i6 == i5   ) {
											// valide ??
											// part 2 
											
											nb_pwd = nb_pwd + 1 ;
											System.out.println("Solution Solver1 " + nb_pwd + " pwd == "  +  pwd );		
										} 	// test validite
									} 		// pwd > LOW 
								}			// int i6 = i5 
							}				// int i5 = i4 
						}					// int i4 = i3 
					}						// int i3 = i2
				}							// int i2 = i1 
			}								// int i1 = 3 
		return "done" ;
	}
}


