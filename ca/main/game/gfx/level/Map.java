package ca.main.game.gfx.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ca.main.game.Game;
import ca.main.game.gfx.SpriteSheet;
import ca.main.game.gfx.SpriteSheetLoader;

public class Map {
	
	SpriteSheetLoader sprite_sheet_loader;
	SpriteSheet tile_sheet;
	
	private BufferedImage grass;
	private BufferedImage rock;
	private BufferedImage voidT;
	
	
	private MapReader mr;
	private static String[][] map;
	
	public Map(Game game, String mapTxtPath){
		
		mr = new MapReader(mapTxtPath);
		map = mr.getMap();
		
		sprite_sheet_loader = game.getSpriteSheetLoader();//fetch SpriteSheet from Game class
		tile_sheet = sprite_sheet_loader.retriveTerrainTile(0);
		
		grass = tile_sheet.grabImage(2, 1, 96, 96, 96, 1);
		rock  = tile_sheet.grabImage(1, 1, 96, 96, 96, 1);
		voidT  = tile_sheet.grabImage(8, 8, 96, 96, 96, 1);
		System.out.println(map.length);
		System.out.println(map[0].length);
	}
	
	public void render(Graphics g, int tileSize, int tileBorder){
		for (int column = 0; column < map.length; column++)
		{
			for (int row = 0; row < map[0].length; row ++){
				
				g.drawImage(drawTile(column, row), (tileSize*column), (tileSize*row), null); 
			}
		}
	}
	
	public BufferedImage drawTile(int column, int row){
		
		String tile_type = map[column][row];


		if(tile_type.equalsIgnoreCase("0")){
			return grass;
		} else if(tile_type.equals("1")) {
			return rock;
		} else{
			return voidT;
		}
		
	}
}
