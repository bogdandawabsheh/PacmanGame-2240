import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Creates a player on board and allows player control
 */
public class Player extends Rectangle
{
	//Universal identifier for Serializable class
	private static final long serialVersionUID = 1L;
	
	
	public boolean right, left, up, down;
	private int speed = 4;
	
/**
 * Constructor for the Player class
 * @param x coordinate for player image spawn
 * @param y coordinate for player image spawn
 */
public Player(int x, int y) 
{
	setBounds(x,y,30,30);
}

/**
 * Player control method
 * @param none
 * @return none
 */
public void tick() 
{//Updates the movements of the player
	if(right && canMove(x+speed,y))x+=speed;
	if(left && canMove(x-speed,y))x-=speed;
	if(up && canMove(x,y-speed))y-=speed;
	if(down && canMove(x,y+speed))y+=speed;
	
	Level level = Game.level;
	
	for(int i = 0; i < level.pellets.size();i++) 
	{//Removes the pellets from the scene when the Pac-Man interacts with them
	if(this.intersects(level.pellets.get(i)))
	{//Adds to the highscore while removing the pellets
		Game.addScore();
		level.pellets.remove(i);
		break;
		}
}
/**When the number of pellets reaches zero, the game restarts and puts the player
	back in its original position*/
	if(level.pellets.size() == 0) 
	{
	//we win
		Game.player = new Player(0,0);
		Game.level = new Level("/map/map.png");
	}

	for(int i = 0; i < Game.level.enemies.size();i++)
	{/**Closes the game window after making contact with a ghost
	sends you back to the main menu*/
		Enemy en = Game.level.enemies.get(i);
		if(en.intersects(this)){
			MainMenuScene.disposeFrame();
		}
				
	}


}
/**
 * Private class for seeing if the player can move in a certain direction
 * @param nextx
 * @param nexty
 * @return True or false depending on outcome.
 */
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

/**
 * Draws the graphic to a specified color
 * @param g
 */
	public void render(Graphics g) 
	{
		g.drawImage(Texture.player,x,y,width,height,null);
	}
	
	
	
}