import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Class for AI control
 */
public class Enemy extends Rectangle
{

	private static final long serialVersionUID = 1L;
	
	private int random = 0,smart = 1,find_path = 2;
	
	private int state = smart;
	
	private int right = 0,left = 1, up = 2, down = 3;
	
	private int dir = -1;
	
	public Random randomGen;
			
	private int time = 0;
	
	private int targetTime = 60*4;

	private int spd = 4;
	
	private int lastDir = -1;
	
	/**
	 * Constructor for the Enemy class
	 * Assigns direction/movement
	 * @param x
	 * @param y
	 */
	public Enemy(int x,int y)
	{
		randomGen = new Random();
		setBounds(x,y,32,32);
		dir = randomGen.nextInt(4);
	}

	/**
	 * Movement control
	 */
		public void tick()
		{
			if(state == random)
			{
	if(dir == right)
	{
		if(canMove(x+spd,y))
		{
			x+=spd;
		}
		else
		{
			dir =  randomGen.nextInt(4);
		}
	}
	else if(dir == left)
	{
		if(canMove(x-spd,y))
		{
			x-=spd;
		}
		else
		{
			dir =  randomGen.nextInt(4);
		}
	}
	else if(dir == up)
	{
		if(canMove(x,y-spd))
		{
			y-=spd;
		}
		else
		{
			dir =  randomGen.nextInt(4);
		}
	}
	else if(dir == down)
		{
		if(canMove(x,y+spd))
		{
			y+=spd;
		}
		else
		{
			dir =  randomGen.nextInt(4);
		}
		}
		time++;
		
		if(time == targetTime) 
			state = smart;
			time = 0;
			}
			else if(state == smart)
			{
				//follow the player
				boolean move = false;
				if(x < Game.player.x)
				{
					if(canMove(x+spd,y))
					{
						x+=spd;
						move = true;
						lastDir = right;
					}
				}
				
				if(x < Game.player.x)
				{
					if(canMove(x-spd,y))
					{
						x-=spd;
						move = true;
						lastDir = left;
					}
				}
				if(x < Game.player.y)
				{
					if(canMove(x,y+spd))
					{
						y+=spd;
						move = true;
						lastDir = down;
					}
				}
				if(x < Game.player.y)
				{
					if(canMove(x,y-spd))
					{
						y-=spd;
						move = true;
						lastDir = up;
					}
				}
				
				if(x == Game.player.x && y == Game.player.y) move = true;
				
				if(!move)
				{
					state = find_path;
				}
				

				
					
				
				time++;
				
				if(time == targetTime*2) 
					state = random;
					time = 0;
				
				
			}
			else if(state == find_path)
				
				
			{
				if(lastDir == right) 
				{
					if(y < Game.player.y) 
					{
						if(canMove(x,y+spd))
						{
							y+=spd;
							state = smart;
						}
					}
					else
					{
						if(canMove(x,y-spd))
						{
							y-=spd;
							state = smart;
						}
					}
					
					if(canMove(x+spd,y))
					{
						x+=spd;
					}
				}
				else if(lastDir == left)
				{
					if(y < Game.player.y) 
					{
						if(canMove(x,y+spd))
						{
							y+=spd;
							state = smart;
						}
					}
					else
					{
						if(canMove(x,y-spd))
						{
							y-=spd;
							state = smart;
						}
					}
					
					if(canMove(x-spd,y))
					{
						x-=spd;
					}
				}
				else if(lastDir == up)
				{
					if(x < Game.player.x) 
					{
						if(canMove(x+spd,y))
						{
							x+=spd;
							state = smart;
						}
					}
					else
					{
						if(canMove(x-spd,y))
						{
							x-=spd;
							state = smart;
						}
					}
					
					if(canMove(x,y-spd))
					{
						y-=spd;
					}
				}
				else if(lastDir == down)
				{
					if(x < Game.player.x) 
					{
						if(canMove(x+spd,y))
						{
							x+=spd;
							state = smart;
						}
					}
					else
					{
						if(canMove(x-spd,y))
						{
							x-=spd;
							state = smart;
						}
					}
					
					if(canMove(x,y+spd))
					{
						y+=spd;
					}
				}
				
				time++;
				
				if(time == targetTime*2) 
					state = random;
					time = 0;
				
				
			}
			}
		
			/**
			 * Checks if the AI can move in a certain direction
			 * @param nextx
			 * @param nexty
			 * @return True or False
			 */
			private boolean canMove(int nextx, int nexty)
			{
				Rectangle bounds = new Rectangle(nextx,nexty,width,height);
				Level level = Game.level;
				
				for(int xx = 0;xx < level.tiles.length;xx++) {
					for(int yy = 0; yy < level.tiles[0].length;yy++) {
						if(level.tiles[xx][yy] != null) {
							if(bounds.intersects(level.tiles[xx][yy])) {
								return false;
							}
						}
					}
				}
				return true;
			}
			
		/**
		 * Draws the AI with the specific color
		 * @param g
		 */
		public void Render(Graphics g) 
		{
			g.drawImage(Texture.ghost,x,y,width,height,null);

		}
	}