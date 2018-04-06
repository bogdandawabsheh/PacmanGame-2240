import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Class esponsible for creation of the game level
 */
public class Level 
{

	public int width;
	public int height;
	
	public Tile[][] tiles;
	
	public List<Pellet> pellets;
	public List <Enemy> enemies;
	
	/**
	 * Constructor for the level class
	 * @param path to the level map
	 */
	public Level(String path) 
	{//Creates an array list for Pellet and Enemy
		pellets = new ArrayList<>();
		enemies = new ArrayList<>();
		
		try {//Creates a map based on the spritesheet provided.
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth();
			this.height = map.getHeight();
			int[] pixels = new int [width*height];
			tiles = new Tile[width][height];
			map.getRGB(0,0, width, height,pixels, 0, width);
			
			for(int xx = 0; xx < width; xx++) 
			{
				for(int yy = 0; yy <height; yy++) {
					int val = pixels[xx + (yy*width)];
					//Tells the program which squares are tiles based on hex code and initiates it
					if(val == 0xFF000000){
						
						tiles[xx][yy] = new Tile(xx*32,yy*32);
					}//Tells the program which tile is associated to the player based on the hex code associated with the player
					else if(val == 0xFF0026FF){
						//Player
						Game.player.x = xx*32;
						Game.player.y = yy*32;
					}//Indictaes which tiles are associated with the enemy by hex code and sets dimensions
					else if(val == 0xFFFF0000)
					{
						//Enemy
						enemies.add(new Enemy(xx*32,yy*32));
					}//Indicates which squares are associated with the pellets and sets dimensions
					else 
					{	//Pellet
						pellets.add(new Pellet(xx*32,yy*32));
					}
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		}
	/**
	 * Class responsible for updating enemies	
	 */
	public void tick()
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).tick();
		}
	}

	/**
	 * Class responsible for rendering game tiles
	 * @param g Render settings
	 */
	public void render(Graphics g) 
	{
	for(int x = 0; x < width; x++) {
		for(int y = 0; y < height; y++) {
			if (tiles[x][y] != null)tiles[x][y].render(g);
		}
	}//Renders the pellets
	for(int i = 0; i < pellets.size(); i++) 
	{
		pellets.get(i).render(g);
	}//Renders the enemies
	for(int i = 0; i < enemies.size(); i++)
	{
		enemies.get(i).Render(g);
	}
	
}

}
