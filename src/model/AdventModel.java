package model;

import java.util.Observable;

public class AdventModel extends Observable{
    private boolean existe;
    private int problem   ;
	   
	   /*
	    * getter and setters
	    */
	   public int getProblem () {
		   return problem;
	   }
	   public void setProblem ( int problem){
		   this.problem =  problem ;
	   }
	   
    void setExiste(boolean existe) {
	this.existe = existe;
	setChanged();
	notifyObservers();
    } 

    
    boolean getExiste() {
	return existe;
    }   

}
