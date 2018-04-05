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
 * @param x
 * @param y
 */
public Tile(int x, int y)
{
	setBounds(x,y,32,32);
}

/**
 * Fills the graphic with the specific color
 * @param g
 */
	public void render(Graphics g) 
	{
		g.setColor(new Color(33,0,127));
		g.fillRect(x, y, width, height);
	}
	
	
}
