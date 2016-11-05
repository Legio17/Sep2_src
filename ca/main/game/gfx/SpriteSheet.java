package ca.main.game.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height, int pixelSize){
		
		BufferedImage img = image.getSubimage((col * pixelSize)+1 - pixelSize, //+1 to ignore frame
											  (row * pixelSize)+1 - pixelSize, 
											   width-2, height-2);
		return img;
	}
}
