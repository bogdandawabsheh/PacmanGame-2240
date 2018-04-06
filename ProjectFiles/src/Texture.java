import java.awt.image.BufferedImage;

public class Texture 
{
public static BufferedImage player;
public static BufferedImage ghost;
	
	public Texture() 

{//Loads the position of the player image in the spritesheet created
	player = Game.spritesheet.getSprite(16, 0);
	//Loads the postion of the ghost image in the spritesheet created
	ghost = Game.spritesheet.getSprite(0, 16);
	 	
}
}