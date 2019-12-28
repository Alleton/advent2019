package model;

public class Moon {

	// le nom de cette lune
	String nom ;
	// la position de cette lune
	int x = 0 ;	
	int y = 0 ;
	int z = 0 ;

	// la vitesse de la lune 'nom'
	int vx = 0 ;	
	int vy = 0 ;
	int vz = 0 ;

	// creator
	public Moon (String ce_nom ) {
		this.nom = ce_nom;
	}
	
	public Moon (String ce_nom , int ce_x ,int ce_y ,int ce_z ,int ce_vx ,int ce_vy , int ce_vz ) {
		this.nom = ce_nom;
		this.x = ce_x ;
		this.y = ce_y ;
		this.z = ce_z ;
		this.vx = ce_vx ;
		this.vy = ce_vy ;
		this.vz = ce_vz ;
	}
	
	private int potential_energy () {
		int pot_energy = 0 ;
		pot_energy = Math. abs(this.x) + Math. abs(this.y) + Math. abs(this.z) ;
		return pot_energy;
	}
	
	private int kinetic_energy () {
		int kin_energy = 0 ;
		kin_energy =  Math. abs(this.vx) + Math. abs(this.vy) + Math. abs(this.vz) ;
		return kin_energy;
	}
	
	
	public int energy () {
		return potential_energy ()  *kinetic_energy () ;
	}
	
	
	
	public 	void visu_lune () {
		System.out.print("lune " + this.nom);
		System.out.print(" x =  " + this.x);
		System.out.print(" y =  " + this.y);
		System.out.print(" z =  " + this.z);
		System.out.print(" vel  = < " );
		System.out.print(" vx = " + this.vx);
		System.out.print(" vy = " + this.vy);
		System.out.print(" vz = " + this.vz);
		System.out.println("  " );
		}
	
	
	public String mem_pos () {
		String mem =  Integer.toString(this.x) ;
		mem = mem.concat(",") ;
		mem = mem.concat(Integer.toString(this.y)) ;
		mem = mem.concat(",") ;
		mem = mem.concat(Integer.toString(this.z)) ;

		mem = mem.concat(",") ;
		mem = mem.concat(Integer.toString(this.vx)) ;

		mem = mem.concat(",") ;
		mem = mem.concat(Integer.toString(this.vy)) ;

		mem = mem.concat(",") ;
		mem = mem.concat(Integer.toString(this.vz)) ;

		mem = mem.concat(",") ;
		
		return mem;
	}
	
	public String mem_pos_axe ( int axe) {
		String mem = "" ;
		switch ( axe) {
		case 0:
			mem = mem.concat(Integer.toString(this.x)) ;
			mem = mem.concat(",") ;
			mem = mem.concat(Integer.toString(this.vx)) ;
			mem = mem.concat(",") ;
			break ;
		case 1:
			mem = mem.concat(Integer.toString(this.y) ) ;
			mem = mem.concat(",") ;
			mem = mem.concat(Integer.toString(this.vy)) ;
			mem = mem.concat(",") ;
			break ;
		case 2:
			mem = mem.concat(Integer.toString(this.z)) ;
			mem = mem.concat(",") ;
			mem = mem.concat(Integer.toString(this.vz)) ;
			mem = mem.concat(",") ;
			break ;
		}
		return mem;
	}  	//mem_pos_axe
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
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
	 * @return the z
	 */
	public int getZ() {
		return z;
	}
	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}
	/**
	 * @return the vx
	 */
	public int getVx() {
		return vx;
	}
	/**
	 * @param vx the vx to set
	 */
	public void setVx(int vx) {
		this.vx = vx;
	}
	/**
	 * @return the vy
	 */
	public int getVy() {
		return vy;
	}
	/**
	 * @param vy the vy to set
	 */
	public void setVy(int vy) {
		this.vy = vy;
	}
	/**
	 * @return the vz
	 */
	public int getVz() {
		return vz;
	}
	/**
	 * @param vz the vz to set
	 */
	public void setVz(int vz) {
		this.vz = vz;
	}

}
