package model;

import java.util.HashMap;

public class Raw_materials {
	String name ;
	int  quantite ;

	/*
	 * createur avec nom et quantite
	 */
	public  Raw_materials ( String nom , int q ) {
		System.out.println(" creation =  Raw_materials "  + nom) ;
		this.name = nom ;
		this.quantite = q;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}
	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	
}
