package model;

public class S11_surface {


	private int x ;
	private int y ;
	private int couleur;

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the couleur
	 */
	public int getCouleur() {
		return couleur;
	}

	/**
	 * @param couleur the couleur to set
	 */
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}

	public S11_surface ( int xx  , int yy  , int color ) {
		this.x = xx ;
		this.y = yy ;
		this.couleur = color ; 
				
	}
}
