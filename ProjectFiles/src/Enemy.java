import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends Rectangle
{

	private static final long serialVersionUID = 1L;

	
	public Enemy(int x,int y)
	{
		setBounds(x,x,32,32);
	}

		public void tick()
		{

		}	
		public void Render(Graphics g) 
		{
			g.setColor(Color.PINK);
			g.fillRect(x, y, width, height);

		}
	}