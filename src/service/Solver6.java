package service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import model.S6_object;

public class Solver6 {


	String solver6_1 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019" );
		String line = null;
		String object_name ;
		String a_name ;

		final String  COM = "COM" ;
		int nb_orbit  = 0 ;

		System.out.println("filename = " + sfname );

		// le vecteur des objets
		Vector les_objets =new Vector();

		// un objet 
		// S6_object mon_objet ; 






		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			while ((line = reader.readLine()) != null) {
				System.out.println("line = " + line );
				String[] parts =line.split( ";" );
				System.out.println( parts[0 ]  ) ; 
				les_objets.add( new S6_object( parts[1 ] ,parts[0 ] )) ; 
			}
			reader.close();

			// fin remplissage
			System.out.println("+++++++++++++++++++") ;
			// visu
			for(int i = 0; i< les_objets.size();i++) {

				S6_object mon_objet = (S6_object) les_objets.get(i) ;

				System.out.println( mon_objet.getName() + " autour de " + mon_objet.getAround() ) ;

				// pour chaque oject name du vecteur
				// tant que around != "COM"
				// on cherche around comme nom dans le vecteur
				// nb orbit + 1
				// name = around

				// fin tant que
				String me_around ;
				Boolean done = false;

				me_around = mon_objet.getAround();

				// while ( ! me_around.equals(COM)  ) {
				while ( 1 == 1 ) {
					// System.out.println(" around " + mon_objet.getAround() ) ; 
					System.out.println(" around " + me_around ) ;
					nb_orbit = nb_orbit + 1 ;
					if ( me_around.equals(COM) ) break ;

					// recherche  du around
					for(int j = 0; j< les_objets.size();j++) {
						S6_object mon_around = (S6_object) les_objets.get(j) ;

						if  ( mon_around.getName().equals(me_around ) ) {
							me_around = mon_around.getAround() ;
							break ;
						}

						//me_around = mon_around.getAround() ;


					}
				}





			}


			return " done " + nb_orbit  ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
	}

	String solver6 (String sfname) {
		// lecture fichier
		System.out.println("Advent2019" );
		String line = null;
		String object_name ;
		String a_name ;

		final String  COM = "COM" ;
		final String  YOU = "YOU" ;
		final String  SAN = "SAN" ;
		int nb_orbit  = 0 ;

		System.out.println("filename = " + sfname );

		// le vecteur des objets
		Vector les_objets =new Vector();
		Vector you_objets =new Vector();
		Vector san_objets =new Vector();

		// hash table de mes orbites
		HashMap<String, Integer> you_orbits = new HashMap<String, Integer>();


		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			while ((line = reader.readLine()) != null) {
				// System.out.println("line = " + line );
				String[] parts =line.split( ";" );
				// System.out.println( parts[0 ]  ) ; 
				les_objets.add( new S6_object( parts[1 ] ,parts[0 ] )) ; 
			}
			reader.close();



			// fin remplissage les_objets
			System.out.println("+++++++++++++++++++") ;
			// visu
			for(int i = 0; i< les_objets.size();i++) {
				// remplissage you_objets :: le vecteur des objets autour desquels je suis
				S6_object mon_objet = (S6_object) les_objets.get(i) ;

				String me_around ;


				me_around = mon_objet.getAround();

				if ( mon_objet.getName().equals(YOU)) {
					// while ( ! me_around.equals(COM)  ) {
					while ( 1 == 1 ) {
						// System.out.println(" around " + mon_objet.getAround() ) ; 
						System.out.println(" around " + me_around ) ;
						nb_orbit = nb_orbit + 1 ;
						you_orbits.put(me_around, nb_orbit) ;
						if ( me_around.equals(COM) ) break ;

						// recherche  du around
						// recherche  du around
						for(int j = 0; j< les_objets.size();j++) {
							S6_object mon_around = (S6_object) les_objets.get(j) ;

							if  ( mon_around.getName().equals(me_around ) ) {
								me_around = mon_around.getAround() ;
								break ;
							}
						}
					}
				}
			}
			for (String etoile : you_orbits.keySet()) {
				System.out.println("key: " + etoile + " value: " + you_orbits.get(etoile));
			}
			
			// recherche depuis SAN
			// fin remplissage
			System.out.println("+++++++++++++++++++") ;
			System.out.println("          SAN ") ;
			System.out.println("+++++++++++++++++++") ;
			// visu
			for(int i = 0; i< les_objets.size();i++) {
				// rechche SAN
				S6_object mon_objet = (S6_object) les_objets.get(i) ;

				String me_around ;


				//me_around = mon_objet.getAround();

				if ( mon_objet.getName().equals(SAN)) {
					System.out.println("          SAN   trouve ") ;
					nb_orbit = 0 ;
					me_around = mon_objet.getAround();
					// 
					while ( 1 == 1 ) {
						// System.out.println(" around " + mon_objet.getAround() ) ;
						
						System.out.println(" around " + mon_objet.getName() + " " +  me_around ) ;
						
						nb_orbit = nb_orbit + 1 ;
						//you_orbits.put(me_around, nb_orbit) ;
						// on cherche dans le hash
						
						if (you_orbits.containsKey(me_around) ){
							// end of it
							System.out.println("Yes  trouve match around   " + mon_objet.getName()  + "  " + me_around ) ;
							System.out.println( " au bout de " + nb_orbit ) ;
							System.out.println("key: " + me_around + " value: " + you_orbits.get(me_around));
							nb_orbit = nb_orbit + you_orbits.get(me_around) - 2 ;   // on va juste d'orbite  en orbite 
							break ;
						} else {
							 //mon_object = (S6_object) les_objets.get(j) ;
							// recherche  du around
							System.out.println("NO  trouve match around   " + me_around ) ;
							
							// recherche pere 
							
							for(int j = 0; j< les_objets.size();j++) {
								S6_object mon_around = (S6_object) les_objets.get(j) ;
								System.out.println(  mon_around.getName()  + " est il   " + me_around ) ;
								
								if  ( mon_around.getName().equals(me_around ) ) {
									System.out.println( "Yes  le pere  de  " + me_around  
											+ " est   " + mon_around.getAround()  + " around " 
											+ mon_around.getName() ) ;
									me_around = mon_around.getAround() ;
									break ;
								}
							}
						}

					}
				}
			}

			return " done " + nb_orbit  ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
	}
}

