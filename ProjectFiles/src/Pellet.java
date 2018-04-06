import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pellet extends Rectangle
{

	private static final long serialVersionUID = 1L;
	public Pellet (int x,int y) 
	{//Sets the interactable size for the pellets
		setBounds(x,y,32,32);

	
}
public void render(Graphics g)
{//Sets the colour of the pellets as well as their size
	g.setColor(Color.orange);
	g.fillRect(x+10, y+10, 8, 8);
}
	
	
	}
