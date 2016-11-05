package ca.main.game.gfx.level;

import java.awt.image.BufferedImage;

import ca.main.game.Game;
import ca.main.game.gfx.SpriteSheet;
import ca.main.game.gfx.SpriteSheetLoader;

public class Map {
	
	SpriteSheetLoader sprite_sheet_loader;
	SpriteSheet tile_sheet;
	
	private BufferedImage grass;
	private BufferedImage rock;
	
	private MapReader mr;
	private static String[][] map;
	
	public Map(Game game){
		
		mr = new MapReader("res/maps/map01.txt");
		map = mr.getMap();
		
		sprite_sheet_loader = game.getSpriteSheetLoader();//fetch SpriteSheet from Game class
		tile_sheet = sprite_sheet_loader.retriveTerrainTile(0);
		
		grass = tile_sheet.grabImage(2, 1, 96, 96, 96, 1);
		rock  = tile_sheet.grabImage(1, 1, 96, 96, 96, 1);
		
	}
}
