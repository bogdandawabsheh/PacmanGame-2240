import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pellet extends Rectangle
{

	private static final long serialVersionUID = 1L;
	public Pellet (int x,int y) 
	{
		setBounds(x,y,32,32);

	
}
public void render(Graphics g)
{
	g.setColor(Color.green);
	g.fillRect(x+10, y+10, 8, 8);
}
	
	
	}
