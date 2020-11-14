package service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import model.Raw_materials;
import model.Reactions;

public class Solver14 {
	
	// HashMap < String, Integer > Reactions = new  HashMap <String ,Integer> ();
	HashMap < String, Integer > besoins = new  HashMap <String ,Integer> ();
	
	//Reactions [] les_reactions  = new Reactions [10]  ;
	HashMap < String, Reactions > les_reactions = new  HashMap <String ,Reactions> ();
	
	final String ORE = "ORE" ; 
	int q_ORE = 0 ;
	

	String solver14 (String sfname) {
		read_line( sfname) ;
		
		// on remplit la hash besoins selon les besoins en commencant par le fuel
		besoins.put("FUEL",1) ;
		predeceseurs("FUEL" , 1) ;
		
		System.out.println("q_ORE =  " + q_ORE );
		return "solver 14" ;
	}	// end solver14
	
	
	/* 
	 * recursivite sur les raws necessaires a un produit
	 */
	void predeceseurs( String produit_final , int quantite) {
		System.out.println("predeceseurs ;  " + produit_final  + " quantite = " + quantite);
		int ma_production = 0 ;
		if ( produit_final.equals(ORE)) {
			// c'est fini
			System.out.println(" fin absurde ") ;
		} else {
			
		}
		HashMap < String, Integer > mes_besoins = new  HashMap <String ,Integer> ();
		Reactions  reaction = les_reactions.get(produit_final) ;
//		for (Map.Entry<String, Integer> pair: reaction.exporte()  {
//            System.out.format("  key: %s, value: %d%n", pair.getKey(), pair.getValue());
//        }
		
		
		ma_production = reaction.getQ_resultat() ;
		mes_besoins = reaction.exporte();
		System.out.println(" mes besoins "  + mes_besoins.toString() ) ;
		
		
		//  et les besoins
		
		for (Map.Entry<String, Integer> pair: mes_besoins.entrySet()) {
            System.out.format("  key: %s, value: %d%n", pair.getKey(), pair.getValue());
            
            System.out.println(" besoin de  "  + pair.getKey() ) ;
            System.out.println("       quantite   "  + pair.getValue() ) ;
            
            
            if ( ! pair.getKey().equals( ORE)) {
            	// recursivite
            	int quantite_necessaire = pair.getValue() * quantite ;
            	// on divise par la production
            	if ( quantite_necessaire % ma_production == 0 ) {
            		quantite_necessaire = quantite_necessaire / ma_production;
            	}else 
            	{
            		 quantite_necessaire = (quantite_necessaire/ ma_production ) + 1 ;
            	}
            	 System.out.println("       quantite necessaire =   "  + quantite_necessaire ) ;  
            	
            	predeceseurs(  pair.getKey() , quantite_necessaire ) ;	
            }
            else {
            	System.out.println("       quantite ORE avant =   "  + q_ORE ) ; 
            	q_ORE= q_ORE + quantite ;
            	System.out.println("       quantite ORE apres =   "  + q_ORE ) ; 
            }
            
        }
		
		
		
//		for (Map.Entry<String, Integer> pair: mes_besoins.entrySet()) {
//			System.out.format("  key: %s, value: %d%n", pair.getKey(), pair.getValue() +
//					" soit  " + pair.getValue()) ;
//			if ( ! pair.getKey().equals( ORE)) {
//				// sinon c'est OK
//				predeceseurs(  pair.getKey() ,  pair.getValue()  ) ;	
//			}
//			
//        }
		
	}
	
	
	

	void read_line( String filename) {
		try {
			String line  ;
			
			//int nb_reactions = 1 ;
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			/* lecture ligne  */
			 
			while ((line = reader.readLine()) != null) {
				Reactions  raw = new  Reactions("",0) ;
				String raw_result = "";
				int    q_result ;
				HashMap < String, Integer > hash_product = new  HashMap <String ,Integer> ();
				
				
				// Reactions  raw = new  Reactions("",0) ;
				
				line = line.trim() ;
				line = line.replace("=> ", "").replace(",", " ");
				line = line.replace(" ", ":").replace("::", ":");
				System.out.println("this line ;  " + line );
				
				String []  parts_string  = line.split(":");
				for ( int i = parts_string.length -1 ; i >= 0 ; i = i- 2  ) {
					//System.out.println("this parts from end ");
					// System.out.println( "i = " + i + " "  + parts_string[i] ) ;
					if ( i == parts_string.length -1   ) {
						raw_result = parts_string[i]  ; 
						q_result   = Integer.parseInt(parts_string[i -1]) ;
						//raw.put(raw_result,q_result  ) ;
						raw.setResultat(raw_result);
						raw.setQ_resultat(q_result);
						
					} else {
						hash_product.put(parts_string[i] , Integer.parseInt(parts_string[i -1]) ) ;
						
					}
					raw.copy_hash(hash_product);
				}
				// les_reactions [nb_reactions] = raw;
				les_reactions.put(raw_result,raw) ;
				//nb_reactions ++ ;
			}
			
			// fermeture 
			reader.close();
			
			Set<String> keys =les_reactions.keySet() ;
			for(String cle : keys){
				   //do someting to anObject...
				les_reactions.get(cle).toString() ;
				}
			
//			Iterator it = les_reactions.entrySet().iterator();
//			while (it.hasNext()) {
//				//les_reactions.Entry pair = (les_reactions.Entry)it.next();
//		        //System.out.println(pair.getKey() + " = " + pair.getValue());
//				System.out.println(les_reactions. ) ;
//		        it.remove(); // avoids a ConcurrentModificationException
//		    }
			System.out.println(" read done "   ) ;
			
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to load " + filename , e);
			//e.printStackTrace();
		}
	} // read line
}
