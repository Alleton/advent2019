package model;

public class S6_object {
	public String name ;
	public Boolean orbiting ;	// false for COM , true for others
	public String around ;		// the object it is oribitig

	
	// creation avec son nom
	public S6_object ( String  nom ){
		this.name = nom ;
	}
	
	public S6_object ( String  nom , String  orbite ){
		this.name = nom ;
		this.around = orbite ;
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
	 * @return the orbiting
	 */
	public Boolean getOrbiting() {
		return orbiting;
	}
	/**
	 * @param orbiting the orbiting to set
	 */
	public void setOrbiting(Boolean orbiting) {
		this.orbiting = orbiting;
	}
	/**
	 * @return the around
	 */
	public String getAround() {
		return around;
	}
	/**
	 * @param around the around to set
	 */
	public void setAround(String around) {
		this.around = around;
	}
}
