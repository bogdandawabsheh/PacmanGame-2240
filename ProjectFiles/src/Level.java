import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Class responsible for drawing the level
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
	 * Draws the level with the image from a path
	 * @param path to the background image
	 */
	public Level(String path) 
	{
		pellets = new ArrayList<>();
		enemies = new ArrayList<>();
		
		try {
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
					
					if(val == 0xFF000000){
						
						tiles[xx][yy] = new Tile(xx*32,yy*32);
					}
					else if(val == 0xFF0026FF){
						//Player
						Game.player.x = xx*32;
						Game.player.y = yy*32;
					}
					else if(val == 0xFFFF0000)
					{
						//Enemy
						enemies.add(new Enemy(xx*32,yy*32));
					}
					else 
					{
						pellets.add(new Pellet(xx*32,yy*32));
					}
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		}
	
	/**
	 * Movement control
	 */
	public void tick()
	{
		for(int i = 0; i < enemies.size();i++)
		{
			enemies.get(i).tick();
		}
	}
	
	/**
	 * Fills the tiles with a specified color
	 * @param g
	 */
	public void render(Graphics g) 
	{
	for(int x = 0; x < width; x++) {
		for(int y = 0; y < height; y++) {
			if (tiles[x][y] != null)tiles[x][y].render(g);
		}
	}
	for(int i = 0; i < pellets.size(); i++) 
	{
		pellets.get(i).render(g);
	}
	
	
}

}