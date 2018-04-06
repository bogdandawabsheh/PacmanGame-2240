import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Tile extends Rectangle 
{
public Tile(int x, int y)
{//Sets the size of the tiles in the game
	setBounds(x,y,32,32);
}
	public void render(Graphics g) 
	{//Renders the tiles
		g.setColor(new Color(33,0,127));
		g.fillRect(x, y, width, height);
	}
	
	
}
