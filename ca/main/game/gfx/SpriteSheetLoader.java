package ca.main.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteSheetLoader{
	
	private SpriteSheet spriteSheet = null;
	private BufferImageLoader loader;
	
	private ArrayList<SpriteSheet> player_model_list = new ArrayList<SpriteSheet>();   
	private ArrayList<SpriteSheet> tile_list = new ArrayList<SpriteSheet>();   
	
	public SpriteSheetLoader(){
		loader = new BufferImageLoader();
		
		//===================== player models ==================
		loadPlayerModel("/sheets/player_models/", "applejack");
		
		//===================== maps ===========================
		loadTile("/sheets/tiles/", "terrain");
	}
	
	public void loadPlayerModel(String folder_path,String player_model_name){
		
		String path = folder_path + player_model_name + ".png";
		
		try {
			spriteSheet = new SpriteSheet(loader.loadImage(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player_model_list.add(spriteSheet);
		
		
	}
	
	public void loadTile(String folder_path,String png_name){
		
		String path = folder_path + png_name + ".png";
		
		try {
			spriteSheet = new SpriteSheet(loader.loadImage(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tile_list.add(spriteSheet);

		
	}
	
	public SpriteSheet retrivePlayerModel(int number){
		return player_model_list.get(number);
	}
	
	public SpriteSheet retriveTerrainTile(int number){
		return tile_list.get(number);
	}
}
