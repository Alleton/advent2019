package model;

public class S10_asteroid {
	int x;
	int y;
	int    numero;
	boolean existe = true;
	boolean vu = false;
	
	double angle = 0 ;
	
	
	/* memorise l'angle par rapport a la base situe en x_base  ,y_base  direction nord  
	 * 
	 */
	public void set_angle ( int x_base , int y_base ){
		// calcul de la tangeante = dx /d y
		double dx ;
		double dy ;
		
		double tangeante = 0 ;
		double arc_tangeante = 0 ;
		
		// System.out.println("set_angle   avec x =  "  +  x  + " et  y = " + y  );
		
		dx = ( (double )(x ) - (double )  (x_base))  ;
		dy = ( (double )(y_base ) - (double )  (y))  ;
		
		// System.out.println("set_angle   avec dx =  "  +  dx + " et dy  = " + dy ) ;  
		
		if ( dy == 0.0 ) {
			if ( dx >0 ) {
				angle =   Math.PI / 2.0 ;
			}else {
				angle =  3.0 * Math.PI / 2.0;
			}
			
		} else {
			tangeante = dx / dy ;
			angle = Math.atan(tangeante) ;
			// quatre quartiers
			if ( dx >= 0 && dy > 0   ){
				// rien a faire
			} else {
				// deuxieme quadrant x positif , y negatif 
				if ( dx >= 0 && dy < 0   ) {
					// System.out.println("set_angle  2 eme quartier" + angle ) ;
					angle =  Math.PI + angle ;
				} else {
					// troisieme quadrant x negatif , y negatif
					if ( dx <= 0 && dy < 0   ) {
						// System.out.println("set_angle  3 eme quartier" + angle ) ;
						angle =   Math.PI + angle ;
					} else {
						// quatrieme quadrant x negatif , y positif
						// System.out.println("set_angle  4 eme quartier" + angle ) ;
						angle =  ( 2.0 *  Math.PI ) + angle ;
					}
				}
			}
		}
	}  // end method set_angle
	
	
	
	
	/**
	 * @return the angle
	 */
	public double getAngle () {
		return angle;
	}
	
	
	/**
	 * @return the vu
	 */
	public boolean isVu() {
		return vu;
	}


	/**
	 * @param vu the vu to set
	 */
	public void setVu(boolean vu) {
		this.vu = vu;
	}


	/**
	 * @return the existe
	 */
	public boolean isExiste() {
		return existe;
	}


	/**
	 * @param existe the existe to set
	 */
	public void setExiste(boolean existe) {
		this.existe = existe;
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


	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}


	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}


	public S10_asteroid ( int n_aster , int nx , int ny) {
		this.numero = n_aster;
		this.x = nx;
		this.y = ny;
		this.existe = true;
		this.vu     = false;
	}
	
	
}
