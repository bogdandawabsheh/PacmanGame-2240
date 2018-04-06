import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends Rectangle
{

	private static final long serialVersionUID = 1L;
	
	private int random = 0, smart = 1,find_path = 2;
	//Adding a random state allowing for ghosts to move around without focusing on player
	private int state = smart;
	//Directions for the ghosts to move
	private int right = 0,left = 1, up = 2, down = 3;
	
	private int dir = -1;
	
	public Random randomGen;
	//Sets the timing of the states to 0
	private int time = 0;
	//Sets the target amount of time to remain in each state
	private int targetTime = 60*4;
	//Speed of the ghosts
	private int spd = 3;
	//If the ghosts were not able to find a path
	private int lastDir = -1;
	
	
	public Enemy(int x,int y)
	{//Initializes random
		randomGen = new Random();
		//Sets the size of the ghosts
		setBounds(x,y,25,25);
	//For the random movemennt, it selects and integer between 0 and 3 to determine a random direction for the ghost
		dir = randomGen.nextInt(4);
	}
		

		public void tick()
		{/**Setting the state to random with the following code allows the ghosts to move freely 
			without focusing on the player for a predetermind amount of time*/
			if(state == random)
			{//If the ghost can move right, then move right
				if(dir == right)
				{
					if(canMove(x+spd,y))
					{
						 x+=spd;
					}
					else
					{
						dir = randomGen.nextInt(4);
					}
				}//If the ghost can move left, then move left
				else if (dir == left) 
				{
					if(canMove(x-spd,y))
					{
						x-=spd;
					}
					else
					{
						dir = randomGen.nextInt(4);
					}
				}//If the ghost can move up, then move up
				else if (dir == up)
				{
					if(canMove(x,y-spd))
					{
					 y-=spd;
					}
					else
					{
						dir = randomGen.nextInt(4);
					}
				}//If the ghost can move down, then move down
				else if(dir == down)
				{
					if(canMove(x,y+spd))
					{
						 y+=spd;
					}
					else
					{
						dir = randomGen.nextInt(4);
					}
				}
				//When the program reaches the target time, it then switches the state to smart, following the player
				time++;
				if(time == targetTime) 
				state = smart;
				time = 0;
			}//The smart state is responsible for following the player
			else if(state == smart)
			{
				//Follow the player
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
				if(x > Game.player.x) 
				{
					if(canMove(x-spd,y))
					{
						 x-=spd;
						move = true;
						lastDir = left;
					}
				}
				if(y < Game.player.y)
				{
					if(canMove(x,y+spd))
					{
					 y+=spd;
						move = true;
						lastDir = down;
					}
				}
				if(y > Game.player.y)
				{
					if(canMove(x,y-spd))
					{
						 y-=spd;
						move = true;
						lastDir = up;
					}
				}
				//If we don't have a move because the ghosts found the player.
				if(x == Game.player.x && y ==Game.player.y) move = true;
				
				if(!move)
				{
					state = find_path;
				}	
				/**Once the target time has been reached in the smart state, the state switches back to random. 
				targetTime is multiplied by two in order to allow the program to stay in the 'smart' state for longer*/
				time++;
				if(time == targetTime*2) 
				state = random;
				time = 0;
				}
			else if(state == find_path)
			{	
				//Keep moving to the right until we can find a path up or down to the player
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
				//Keep moving to the left until we can find a path up or down to the player
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
				//Keep moving up until we can find a path left or right to the player
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
				//Keep moving down until we can find a path left or right to the player
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

		//Method copied from the player class, determines if the object is colliding with the wall tiles, not allowing the player or ghost to clip through the
		private boolean canMove(int nextx, int nexty)
		{
			Rectangle bounds = new Rectangle(nextx,nexty,width,height);
			Level level = Game.level;
			
			for(int xx = 0;xx < level.tiles.length;xx++) {
				for(int yy = 0; yy <level.tiles[0].length;yy++) {
					if(level.tiles[xx][yy] != null) {
						if(bounds.intersects(level.tiles[xx][yy])) {
							return false;
						}
					}
				}
			}
			return true;
		}
		
			
		public void Render(Graphics g) 
		{
			g.drawImage(Texture.ghost,x,y,width,height,null);

		}
	}