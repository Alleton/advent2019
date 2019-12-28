package model;

public class Tile {
/**
 *  les differentes tiles
 *  0 is an empty tile. No game object appears in this tile.
 * 1 is a wall tile. Walls are indestructible barriers.
 * 2 is a block tile. Blocks can be broken by the ball.
 * 3 is a horizontal paddle tile. The paddle is indestructible.
 * 4 is a ball tile. The ball moves diagonally and bounces off objects.
 */
	int x;
	int y;
	int type ;
	
	public Tile ( int x , int y , int id ) {
		this.x = x ;
		this.y = y ;
		this .type = id ;
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
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
}
