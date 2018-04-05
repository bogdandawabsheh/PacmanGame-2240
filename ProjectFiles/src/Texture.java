import java.awt.image.BufferedImage;

/**
 * Retrieves the spritesheets for the player and ghosts
 */
public class Texture 
{
public static BufferedImage player;
public static BufferedImage ghost;
	
/**
 * Constructor for the Texture Class
 * Retrieves sprite images
 */
	public Texture() 

{
	player = Game.spritesheet.getSprite(0, 0);
	ghost = Game.spritesheet.getSprite(0, 16);
	 	
}
}