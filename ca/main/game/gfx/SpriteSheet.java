package ca.main.game.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height, int pixelSize, int borderPixels){
		
		BufferedImage img = image.getSubimage((col * pixelSize)+borderPixels - pixelSize, //+1 to ignore frame
											  (row * pixelSize)+borderPixels - pixelSize, 
											   width-2*borderPixels, height-2*borderPixels);
		return img;
	}
}
