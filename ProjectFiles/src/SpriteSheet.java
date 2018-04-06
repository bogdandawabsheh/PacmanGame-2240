import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Retrieves a sprite image for the characters
 */
public class SpriteSheet 
{
	
	private BufferedImage sheet;

	/**
	 * Constructor for the SpriteSheet class
	 * Retrieves the image from the path
	 * @param Image path
	 */
	public SpriteSheet(String path)
	{
		try {
		sheet = ImageIO.read(getClass().getResource(path));
		}
		catch (IOException e)
		{//Message for when the spritesheet fails to load
			System.out.println("Failed to load image");
		}
	}
	
	/**
	 * Gets a 16x16 sprite at the coordinates
	 * @param xx x coordinate
	 * @param yy y coordinate
	 * @return Sprite
	 */
	public BufferedImage getSprite(int xx,int yy)
	{
		return sheet.getSubimage(xx,yy,16,16);
	}

}
	