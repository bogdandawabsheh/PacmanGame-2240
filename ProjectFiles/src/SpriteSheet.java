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
	 * @param Image path
	 */
	public SpriteSheet(String path)
{
		try {	
		sheet = ImageIO.read(getClass().getResource(path));
		}
		catch (IOException e)
		{
			System.out.println("Failed to load image");
		}
}

	/**
	 * Gets a 16x16 sprite at the coordinates
	 * @param xx
	 * @param yy
	 * @return
	 */
	public BufferedImage getSprite(int xx,int yy)
	{
		return sheet.getSubimage(xx, yy,16,16);
	}

	}
	