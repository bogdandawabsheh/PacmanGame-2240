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
	//Loads the position of the player image in the spritesheet created
	player = Game.spritesheet.getSprite(16, 0);
	//Loads the postion of the ghost image in the spritesheet created
	ghost = Game.spritesheet.getSprite(0, 16);
	}
}