package model;

public class S3_point {
	private int x ;
	private int y ;
	
	public S3_point ( int xx , int yy ) {
		this.x = xx ;
		this.y = yy ;
		
	}
	
	public int  eloignement () {
		return ( Math.abs(x) + Math.abs(y)) ;
	}
	
	@Override
	public boolean equals (Object obj) {
		S3_point  other  =(S3_point ) obj ; 
		//Personne other = (Personne) obj;
		if ( this.x != other.x) {
			return false ;
		}
		if ( this.y != other.y) {
			return false ;
		}
		return true ;
	}

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
}	


