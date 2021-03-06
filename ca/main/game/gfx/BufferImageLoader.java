package ca.main.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Legio-PC
 *	simple load image from folder class
 */
public class BufferImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String path) throws IOException{ 	
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
}
