package model;

import java.util.HashMap;
import java.util.Map;

public class Reactions {

	String resultat ;
	int    q_resultat ; // quantite de resultat
	
	// les besoins
	// raw mateial , quantite entree
	HashMap < String, Integer > hash_entry = new  HashMap <String ,Integer> ();

	// Creation 
	public Reactions ( String nom  , int    q  ) {
		//System.out.println(" creation =  Reactions "  + nom) ;
		this.resultat = nom ;
		this.q_resultat = q ;
		// this .type = id ;
	}
	
	// visu une reaction
	
	public String toString() {
		String visu = " produit de reaction  "  + this.resultat + " q = " + this.q_resultat ;
		System.out.println(visu);
		
		//  et les besoins
		
		for (Map.Entry<String, Integer> pair: hash_entry.entrySet()) {
            System.out.format("  key: %s, value: %d%n", pair.getKey(), pair.getValue());
        }
		
		return visu;
	}
	
	/*
	 * exporte la has map
	 */
	 public HashMap<String, Integer> exporte () {
		 return hash_entry ;
	 }
	
	/*
	 * copy la hasmap 
	 */
	public void copy_hash ( HashMap<String, Integer> hash_map ) {
		this.hash_entry.putAll(hash_map); 
	}
	
	/**
	 * @return the resultat
	 */
	public String getResultat() {
		return resultat;
	}

	/**
	 * @param resultat the resultat to set
	 */
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	/**
	 * @return the q_resultat
	 */
	public int getQ_resultat() {
		return q_resultat;
	}

	/**
	 * @param q_resultat the q_resultat to set
	 */
	public void setQ_resultat(int q_resultat) {
		this.q_resultat = q_resultat;
	}
	
	
	
}
