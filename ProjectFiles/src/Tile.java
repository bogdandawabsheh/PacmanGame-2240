import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Class responsible for drawing a tiles on the board
 */
@SuppressWarnings("serial")
public class Tile extends Rectangle 
{
	/**
	 * Constructor for the tile class
	 * @param x starting x point
	 * @param y starting y point
	 */
	public Tile(int x, int y)
	{
		//Sets the size of the tiles in the game
		setBounds(x,y,32,32);
	}
	
	/**
	 * Fills the graphic with the specific color
	 * @param g
	 */
	public void render(Graphics g) 
	{
		//Renders the tiles
		g.setColor(new Color(33,0,127));
		g.fillRect(x, y, width, height);
	}
	
	
}
