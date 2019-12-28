package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;


import org.apache.commons.lang3.StringUtils;

public class Solver8 {

	
	int layer_wide = 25 ;
	int layer_tall = 6 ;
	int layersize = layer_wide * layer_tall ; // taille en pixel d'un layer
	int line_size = 0;
	
	
	String solver8 (String sfname) {
		int resultat = 0 ;
		

		
		int line_length = 0 ;
		int line_number = 0 ;
	    
		String line = null;
		System.out.println("filename = " + sfname );
		// part 2 
		// inputs produces the output 19690720

		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			line = reader.readLine() ; 
			// fermeture 
			reader.close();

			System.out.println(" read done "   ) ;
			line = line.trim() ;
			
			line_length = line.length() ;
			System.out.println("line_length line  = " + line_length ) ;
			line_number = line_length /  layersize;
			System.out.println("line_number line  = " + line_number ) ;
			
			
			// matcher = pattern.matcher(line.substring(layersize * ( line_number - 1 )  ,  layersize * (line_number)   ));
			
			
			
			System.out.println(" deb layersize * ( line_number - 1 )  " + layersize * ( line_number - 1 )    );
			System.out.println(" fin  layersize * (line_number)       " +  layersize * (line_number) );
			
			//String visible_layer = line.substring(layersize * ( line_number - 1 )  ,  layersize * (line_number)   )  ;	// le bottom
			
			String visible_layer = line.substring(0  ,  layersize   )  ;	// le top
			System.out.println("" +visible_layer );
			
			visu ( visible_layer) ;
			
			
			System.out.println(" "  );
			System.out.println("debut descente  "  );
			System.out.println(" "  );
			
			// passage des layers en descendant

for ( int layernb = 1  ; layernb < line_number  ; layernb ++   ) {
	
	
	String layer = line.substring(layersize * ( layernb  )  ,  layersize * (layernb + 1)   )  ;
	
	System.out.println( "line  " + layernb + "   " + layer  );

	visible_layer = my_replace ( visible_layer, layer ) ;
	visu ( visible_layer) ;
	
	

}

System.out.println( "final   "   );
			
			
			
			
visu_final ( visible_layer) ;
			
			return " done solver  8 = " + resultat ;

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
	}
	
	String solver8_1 (String sfname) {

		int resultat = 0 ;
		
		int layer_wide = 25 ;
		int layer_tall = 6 ;
		int layersize = layer_wide * layer_tall ; // taille en pixel d'un layer
		int line_size = 0;
		
		int line_length = 0 ;
		int line_number = 0 ;

		int nb0 = 0;
		int nb1 = 0 ; 
		int nb2 = 0;
		
		int min0 = layersize;        // nb mini de zero
		
		
		
		
		// pour regex
	     Pattern pattern;
	     Matcher matcher;
	    
		String line = null;
		System.out.println("filename = " + sfname );
		// part 2 
		// inputs produces the output 19690720

		try {
			BufferedReader reader = new BufferedReader(new FileReader(sfname));
			/* lecture premiere ligne  */
			line = reader.readLine() ; 
			// fermeture 
			reader.close();

			System.out.println(" read done part 1"   ) ;
			line = line.trim() ;
			int pointeur = 0 ;			// pointeur programme
			// parseline
			//System.out.println("parseline line  = " + line ) ;
			
			line_length = line.length() ;
			System.out.println("line_length line  = " + line_length ) ;
			line_number = line_length /  layersize;
			System.out.println("line_number line  = " + line_number ) ;
			
			
			
			
			System.out.println("line_length line  = " + line_length ) ;
			line_number = line_length /  layersize;
			System.out.println("line_number line  = " + line_number ) ;
			
						 
			 
			// boucle sur les lignes
			for ( int i =0  ; i < line_number ; i++) {
				pattern = Pattern.compile("0");
		        matcher = pattern.matcher(line.substring(layersize *i  ,  layersize * (i +1)   ));
		        
		        
		        nb0 = 0;
		        while(matcher.find()) {
		        	nb0 ++;
		        }
		        System.out.println("line_number line  = " + i + "nb de 0   "  + nb0 ) ;
		        if ( min0 > nb0 ) {
		        	// inutile si plus de zero ..
		        	min0 = nb0 ;
		        	
		        	pattern = Pattern.compile("1");
		        	matcher = pattern.matcher(line.substring(layersize *i  ,  layersize * (i +1)   ));
		        	nb1 = 0;
			        while(matcher.find()) {
			        	nb1 ++;
			        }
			        
			        pattern = Pattern.compile("2");
		        	matcher = pattern.matcher(line.substring(layersize *i  ,  layersize * (i +1)   ));
		        	nb2 = 0;
			        while(matcher.find()) {
			        	nb2 ++;
			        }
			        // 
			        resultat = nb1 *  nb2  ;
		        }
			}
			
			return " done solver  8 = " + resultat ;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Unable to load " + sfname, e);
			//e.printStackTrace();
		}
	}
	
	void visu ( String layer ) {
		for ( int j = 0 ; j < layer_tall ; j ++ ) {
			//	pour la hauteur
			for ( int k = 0 ; k < layer_wide ; k++ ) {
				if ( layer.substring(j* layer_wide +  k  , j * layer_wide + k + 1  ).equals("0") ) {
					System.out.print("0"  );
				} else {
					if  (layer.substring(j* layer_wide +  k  , j * layer_wide + k + 1  ).equals("1") ) {
						System.out.print("1"  );
					} else 
					
					System.out.print(" "  );
				}
			}
			System.out.println(""  );
			
		}

	}

	
	void visu_final ( String layer ) {
		for ( int j = 0 ; j < layer_tall ; j ++ ) {
			//	pour la hauteur
			for ( int k = 0 ; k < layer_wide ; k++ ) {
				if ( layer.substring(j* layer_wide +  k  , j * layer_wide + k + 1  ).equals("0") ) {
					System.out.print(" "  );
				} else 
					System.out.print("*"  );
			}
			System.out.println(""  );
		}
	}		// visu_final

	
	public String my_replace(String upper_layer , String lower_layer) {  
	           int len = upper_layer.length();  
	           
	           // si upper layer est transparent on prend lower layer
	           
	           int i = 0;  
	           char[] resultat = upper_layer.toCharArray();
	           char[] motif    = lower_layer.toCharArray();
	           
	  
	           while (++i < len) {  
	               if (resultat[i] == '2') {  
	            	   resultat[i] = motif[i] ;
	                     
	               }  
	   } 
	           return String.valueOf(resultat);
	}		// end my_replace
	
}
